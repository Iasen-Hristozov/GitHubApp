package test.githubapp.viewmodel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import test.githubapp.model.User;

public class UsersViewModel extends AndroidViewModel
{
   public MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

   public UsersViewModel(@NonNull Application application)
   {
      super(application);
   }

   public void setUsersLiveData(List<User> users)
   {
      usersLiveData.setValue(users);
   }
}
