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
import test.githubapp.model.Contributor;
import test.githubapp.model.GitHubApiService;
import test.githubapp.model.User;

public class ContributorsViewModel extends AndroidViewModel
{
   public MutableLiveData<List<User>> contributorsLiveData = new MutableLiveData<>();
   private final GitHubApiService gitHubApiService = new GitHubApiService("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");
   private final CompositeDisposable disposable = new CompositeDisposable();


   public ContributorsViewModel(@NonNull Application application)
   {
      super(application);
   }

   public void fetchFromRemote(String owner, String repository)
   {
      disposable.add(
            gitHubApiService.getContributors(owner, repository)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<List<User>>()
                            {
                               @Override
                               public void onSuccess(@io.reactivex.annotations.NonNull List<User> contributors)
                               {
                                  contributorRetrieved(contributors);
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

   private void contributorRetrieved(List<User> contributors)
   {
      contributorsLiveData.setValue(contributors);
//      repositoriesLoadError.setValue(false);
//      loading.setValue(false);
   }
}