package test.githubapp.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import test.githubapp.model.GitHubApiService;
import test.githubapp.model.User;
import test.githubapp.util.SharedPreferencesHelper;

public class FollowersViewModel extends AndroidViewModel
{
   public MutableLiveData<List<User>> followersLiveData = new MutableLiveData<>();
   private final GitHubApiService gitHubApiService;
   private final CompositeDisposable disposable;

   public FollowersViewModel(@NonNull Application application)
   {
      super(application);

      SharedPreferencesHelper preferencesHelper = SharedPreferencesHelper.getInstance(application);

      gitHubApiService = new GitHubApiService(preferencesHelper.getToken());

      disposable = new CompositeDisposable();
   }

   public void fetchFromRemote(String owner)
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
//                                  repositoriesLoadError.setValue(true);
//                                  loading.setValue(false);
//                                  e.printStackTrace();
                               }
                            }));
   }

   private void followersRetrieved(List<User> followers)
   {
      followersLiveData.setValue(followers);
//      repositoriesLoadError.setValue(false);
//      loading.setValue(false);
   }
}
