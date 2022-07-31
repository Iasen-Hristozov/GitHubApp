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
import test.githubapp.model.Permissions;
import test.githubapp.model.Repository;
import test.githubapp.model.User;
import test.githubapp.util.SharedPreferencesHelper;

public class OwnerViewModel extends AndroidViewModel
{
   public MutableLiveData<User> ownerLiveData = new MutableLiveData<>();
   public MutableLiveData<Boolean> ownerLoadError = new MutableLiveData<>();
   public MutableLiveData<Boolean> loading = new MutableLiveData<>();

   private final GitHubApiService gitHubApiService;
   private final CompositeDisposable disposable;

   private final SharedPreferencesHelper preferencesHelper;

   public OwnerViewModel(@NonNull Application application)
   {
      super(application);

      preferencesHelper = SharedPreferencesHelper.getInstance(application);

      gitHubApiService = new GitHubApiService(preferencesHelper.getToken());

      disposable = new CompositeDisposable();
   }

   public void setOwnerLiveData(User owner)
   {
      ownerLiveData.setValue(owner);
   }

   private void fetchFromRemote()
   {
      loading.setValue(true);
      disposable.add(
            gitHubApiService.getUser()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<User>()
                            {
                               @Override
                               public void onSuccess(@io.reactivex.annotations.NonNull User user)
                               {
                                  Toast.makeText(getApplication(),
                                                 "Owner retrieved from endpoint",
                                                 Toast.LENGTH_SHORT)
                                       .show();
                                  ownerRetrieved(user);
                               }

                               @Override
                               public void onError(@io.reactivex.annotations.NonNull Throwable e)
                               {
                                  ownerLoadError.setValue(true);
                                  loading.setValue(false);
                                  e.printStackTrace();
                               }
                            }));
   }

   private void ownerRetrieved(User user)
   {
      ownerLiveData.setValue(user);
      ownerLoadError.setValue(false);
      loading.setValue(false);
   }

   @Override
   protected void onCleared()
   {
      super.onCleared();
      disposable.clear();

//      if(insertTask != null)
//      {
//         insertTask.cancel(true);
//         insertTask = null;
//      }
//
//      if(retrieveTask != null)
//      {
//         retrieveTask.cancel(true);
//         retrieveTask = null;
//      }
   }

   public void refresh()
   {
      fetchFromRemote();

//      checkCacheDuration();
//      long updateTime = preferencesHelper.getUpdateTime();
//      long currentTime = System.nanoTime();
//      if(updateTime != 0 && currentTime - updateTime < refreshTime)
//         fetchFromDatabase();
//      else
//         fetchFromRemote();
   }
}
