package test.githubapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import test.githubapp.model.User;

public class OwnerViewModel extends AndroidViewModel
{
   public MutableLiveData<User> ownerLiveData = new MutableLiveData<>();

   public OwnerViewModel(@NonNull Application application)
   {
      super(application);
   }

   public void setOwnerLiveData(User owner)
   {
      ownerLiveData.setValue(owner);
   }
}
