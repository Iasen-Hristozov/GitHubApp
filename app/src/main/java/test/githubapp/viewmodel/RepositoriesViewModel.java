package test.githubapp.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import test.githubapp.model.GitHubApiService;
import test.githubapp.model.GitHubDatabase;
import test.githubapp.model.Permissions;
import test.githubapp.model.PermissionsDao;
import test.githubapp.model.Repository;
import test.githubapp.model.RepositoryDao;
import test.githubapp.model.UsersDao;
import test.githubapp.util.SharedPreferencesHelper;

public class RepositoriesViewModel extends AndroidViewModel
{
   public MutableLiveData<List<Repository>> repositories          = new MutableLiveData<>();
   public MutableLiveData<Boolean>          repositoriesLoadError = new MutableLiveData<>();
   public MutableLiveData<Boolean>          loading               = new MutableLiveData<>();

   private final GitHubApiService    gitHubApiService = new GitHubApiService("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");
   private final CompositeDisposable disposable       = new CompositeDisposable();

   private AsyncTask<List<Repository>, Void, List<Repository>> insertTask;
   private AsyncTask<Void, Void, List<Repository>> retrieveTask;

   private final SharedPreferencesHelper prefHelper  = SharedPreferencesHelper.getInstance(getApplication());
   private       long                    refreshTime = 5 * 60 * 1000 * 1000 * 1000L;

   RepositoryDao repositoriesDao = GitHubDatabase.getInstance(getApplication())
                                                 .repositoriesDao();

   UsersDao usersDao = GitHubDatabase.getInstance(getApplication())
                                     .usersDao();
   PermissionsDao permissionsDao = GitHubDatabase.getInstance(getApplication())
                                                 .permissionsDao();

   public RepositoriesViewModel(@NonNull Application application)
   {
      super(application);
   }

   public void refresh()
   {
      checkCacheDuration();
      long updateTime = prefHelper.getUpdateTime();
      long currentTime = System.nanoTime();
      if(updateTime != 0 && currentTime - updateTime < refreshTime)
         fetchFromDatabase();
      else
         fetchFromRemote();
   }

   public void refreshBypassCache()
   {
      fetchFromRemote();
   }

   private void checkCacheDuration()
   {
      String cachePreference = prefHelper.getCachedDuration();

      if(!cachePreference.equals(""))
      {
         try
         {
            int cachePreferenceInt = Integer.parseInt(cachePreference);
            refreshTime = cachePreferenceInt * 1000 * 1000 * 1000L;
         }
         catch(NumberFormatException e)
         {
            e.printStackTrace();
         }
      }
   }

   private void fetchFromDatabase()
   {
      loading.setValue(true);
      retrieveTask = new RetrieveRepositoriesTask();
      retrieveTask.execute();
   }

   private void fetchFromRemote()
   {
      disposable.add(
            gitHubApiService.getRepositories()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<Repository>>()
                       {
                          @Override
                          public void onSuccess(@io.reactivex.annotations.NonNull List<Repository> repositories)
                          {

                             insertTask = new InsertRepositoriesTask();
                             insertTask.execute(repositories);
                             Toast.makeText(getApplication(),
                                            "Repositories retrieved from endpoint",
                                            Toast.LENGTH_SHORT)
                                  .show();
//                             NotificationsHelper.getInstance(getApplication()).createNotification();
                          }

                          @Override
                          public void onError(@io.reactivex.annotations.NonNull Throwable e)
                          {
                             repositoriesLoadError.setValue(true);
                             loading.setValue(false);
                             e.printStackTrace();
                          }
                       }));
   }

   private void repositoriesRetrieved(List<Repository> repositoriesList)
   {
      repositories.setValue(repositoriesList);
      repositoriesLoadError.setValue(false);
      loading.setValue(false);
   }

   @Override
   protected void onCleared()
   {
      super.onCleared();
      disposable.clear();

      if(insertTask != null)
      {
         insertTask.cancel(true);
         insertTask = null;
      }

      if(retrieveTask != null)
      {
         retrieveTask.cancel(true);
         retrieveTask = null;
      }
   }

   private class InsertRepositoriesTask extends AsyncTask<List<Repository>, Void, List<Repository>>
   {

      @SafeVarargs
      @Override
      protected final List<Repository> doInBackground(List<Repository>... repositories)
      {
         List<Repository> list = repositories[0];
//         RepositoryDao dao = GitHubDatabase.getInstance(getApplication())
//                                           .repositoryDao();
         repositoriesDao.deleteAllRepositories();

         ArrayList<Repository> newList = new ArrayList<>(list);

//         UsersDao usersDao = GitHubDatabase.getInstance(getApplication())
//                                           .ownerDao();
//
//         PermissionsDao permissionsDao = GitHubDatabase.getInstance(getApplication())
//                                                       .permissionsDao();


         for(Repository repository: newList)
         {
            long ownerId = usersDao.insert(repository.getOwner());
            repository.setOwnerId((int)ownerId);

            Permissions permissions = repository.getPermissions();
            permissions.setRepositoryId(repository.getId());
            permissionsDao.insert(permissions);
         }
//
//         List<Long> result = dao.insertAll(newList.toArray(new Repository[0]));
//         int i = 0;
//         while(i < list.size())
//         {
//            list.get(i).setId(result.get(i).intValue());
//            ++i;
//         }
//
//         return list;
         return newList;
      }

      @Override
      protected void onPostExecute(List<Repository> dogBreeds)
      {
         super.onPostExecute(dogBreeds);
         repositoriesRetrieved(dogBreeds);
         prefHelper.saveUpdateTime(System.nanoTime());
      }
   }

   private class RetrieveRepositoriesTask extends AsyncTask<Void, Void, List<Repository>>
   {

      @Override
      protected List<Repository> doInBackground(Void... voids)
      {
//         GitHubDatabase db = GitHubDatabase.getInstance(getApplication());
//         List<Repository> repositories = db.repositoryDao().getAllRepositories();
         List<Repository> repositories = repositoriesDao.getAllRepositories();

         for(Repository repository: repositories)
            repository.setOwner(usersDao.getUser(repository.getOwnerId()));

         return repositories;
      }

      @Override
      protected void onPostExecute(List<Repository> repositories)
      {
         repositoriesRetrieved(repositories);
         Toast.makeText(getApplication(),
                        "Repositories retrieved from database.",
                        Toast.LENGTH_SHORT).show();
      }
   }
}
