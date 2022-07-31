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
import test.githubapp.model.Repository;
import test.githubapp.model.RepositoryDao;
import test.githubapp.model.User;
import test.githubapp.model.UsersDao;
import test.githubapp.util.SharedPreferencesHelper;

class UserDataViewModel extends AndroidViewModel
{
   public MutableLiveData<List<User>> followersLiveData = new MutableLiveData<>();
   public MutableLiveData<List<User>> followingLiveData = new MutableLiveData<>();
   public MutableLiveData<List<Repository>> repositories = new MutableLiveData<>();
   public MutableLiveData<Boolean> loadError = new MutableLiveData<>();
   public MutableLiveData<Boolean> loading = new MutableLiveData<>();

   private AsyncTask<List<Repository>, Void, List<Repository>> insertRepositoriesTask;
   private AsyncTask<Void, Void, List<Repository>> retrieveRepositoriesTask;

   private final GitHubApiService gitHubApiService;
   private final CompositeDisposable disposable;

   private final RepositoryDao repositoriesDao;
   private final UsersDao usersDao;

   private final SharedPreferencesHelper preferencesHelper;

   public UserDataViewModel(@NonNull Application application)
   {
      super(application);

      preferencesHelper = SharedPreferencesHelper.getInstance(application);

      gitHubApiService = new GitHubApiService(preferencesHelper.getToken());

      disposable = new CompositeDisposable();

      repositoriesDao = GitHubDatabase.getInstance(application)
                                      .repositoriesDao();
      usersDao = GitHubDatabase.getInstance(application)
                               .usersDao();

   }

   public void fetchFollowersFromRemote(String owner)
   {
      disposable.add(
            gitHubApiService.getFollowers(owner)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<User>>()
                            {
                               @Override
                               public void onSuccess(@io.reactivex.annotations.NonNull List<User> followers)
                               {
                                  followersRetrieved(followers);
//                                  insertTask = new RepositoriesViewModel.InsertRepositoriesTask();
//                                  insertTask.execute(repositories);
//                                  Toast.makeText(getApplication(),
//                                                 "Repositories retrieved from endpoint",
//                                                 Toast.LENGTH_SHORT)
//                                       .show();
                               }

                               @Override
                               public void onError(@io.reactivex.annotations.NonNull Throwable e)
                               {
                                  loadError.setValue(true);
                                  loading.setValue(false);
                                  e.printStackTrace();
                               }
                            }));
   }

   private void followersRetrieved(List<User> followers)
   {
      followersLiveData.setValue(followers);
      loadError.setValue(false);
      loading.setValue(false);
   }

   public void fetchFollowingFromRemote(String owner)
   {
      disposable.add(
            gitHubApiService.getFollowing(owner)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<User>>()
                            {
                               @Override
                               public void onSuccess(@io.reactivex.annotations.NonNull List<User> following)
                               {
                                  followingRetrieved(following);
//                                  insertTask = new RepositoriesViewModel.InsertRepositoriesTask();
//                                  insertTask.execute(repositories);
//                                  Toast.makeText(getApplication(),
//                                                 "Repositories retrieved from endpoint",
//                                                 Toast.LENGTH_SHORT)
//                                       .show();
                               }

                               @Override
                               public void onError(@io.reactivex.annotations.NonNull Throwable e)
                               {
                                  loadError.setValue(true);
                                  loading.setValue(false);
                                  e.printStackTrace();
                               }
                            }));
   }

   private void followingRetrieved(List<User> following)
   {
      followingLiveData.setValue(following);
      loadError.setValue(false);
      loading.setValue(false);
   }

   private void fetchRepositoriesFromRemote()
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

                                  insertRepositoriesTask = new InsertRepositoriesTask();
                                  insertRepositoriesTask.execute(repositories);
                                  Toast.makeText(getApplication(),
                                                 "Repositories retrieved from endpoint",
                                                 Toast.LENGTH_SHORT)
                                       .show();
                               }

                               @Override
                               public void onError(@io.reactivex.annotations.NonNull Throwable e)
                               {
                                  loadError.setValue(true);
                                  loading.setValue(false);
                                  e.printStackTrace();
                               }
                            }));
   }

   private void repositoriesRetrieved(List<Repository> repositoriesList)
   {
      repositories.setValue(repositoriesList);
      loadError.setValue(false);
      loading.setValue(false);
   }

   private class InsertRepositoriesTask extends AsyncTask<List<Repository>, Void, List<Repository>>
   {

      @SafeVarargs
      @Override
      protected final List<Repository> doInBackground(List<Repository>... repositories)
      {
         List<Repository> list = repositories[0];

         repositoriesDao.deleteAllRepositories();

         ArrayList<Repository> newList = new ArrayList<>(list);

         for(Repository repository : newList)
         {
            long ownerId = usersDao.insert(repository.getOwner());
            repository.setOwnerId((int) ownerId);

         }
         return newList;
      }

      @Override
      protected void onPostExecute(List<Repository> dogBreeds)
      {
         super.onPostExecute(dogBreeds);
         repositoriesRetrieved(dogBreeds);
         preferencesHelper.saveUpdateTime(System.nanoTime());
      }
   }

   private class RetrieveRepositoriesTask extends AsyncTask<Void, Void, List<Repository>>
   {

      @Override
      protected List<Repository> doInBackground(Void... voids)
      {
         List<Repository> repositories = repositoriesDao.getAllRepositories();

         for(Repository repository : repositories)
         {
            repository.setOwner(usersDao.getUser(repository.getOwnerId()));
         }

         return repositories;
      }

      @Override
      protected void onPostExecute(List<Repository> repositories)
      {
         repositoriesRetrieved(repositories);
         Toast.makeText(getApplication(),
                        "Repositories retrieved from database.",
                        Toast.LENGTH_SHORT)
              .show();
      }
   }

}
