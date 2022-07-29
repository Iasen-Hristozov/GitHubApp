package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.security.acl.Owner;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import test.githubapp.R;
import test.githubapp.model.User;
import test.githubapp.util.SharedPreferencesHelper;
import test.githubapp.viewmodel.ContributorsViewModel;
import test.githubapp.viewmodel.FollowersViewModel;
import test.githubapp.viewmodel.FollowingViewModel;
import test.githubapp.viewmodel.OwnerViewModel;
import test.githubapp.viewmodel.RepositoriesViewModel;

public class LoginFragment extends Fragment
{
   private RepositoriesViewModel repositoriesViewModel;

   public LoginFragment()
   {
      // Required empty public constructor
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   private String token;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_login, container, false);

      EditText tokenEditText = view.findViewById(R.id.tokenEditText);
      Button loginButton = view.findViewById(R.id.loginButton);
      loginButton.setOnClickListener(v -> {
         token = tokenEditText.getText().toString();
         if(token.isEmpty())
            tokenEditText.setError("You must enter a token");
         else
         {
            SharedPreferencesHelper.getInstance(getContext()).saveToken(token);
            getUserInfo(token);
         }
      });

      return view;
   }

   private void getUserInfo(String token)
   {
      repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
      repositoriesViewModel.refresh();
      observeViewModel();
   }

   private void observeViewModel()
   {
      repositoriesViewModel.repositories.observe(requireActivity(), repositories -> {
         if(repositories != null && repositories instanceof List)
         {
            User owner = repositories.get(0).getOwner();
            OwnerViewModel ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);
            ownerViewModel.setOwnerLiveData(owner);

            FollowersViewModel followersViewModel = new ViewModelProvider(requireActivity()).get(FollowersViewModel.class);
            followersViewModel.fetchFromRemote(owner.getLogin());

            FollowingViewModel followingViewModel = new ViewModelProvider(requireActivity()).get(FollowingViewModel.class);
            followingViewModel.fetchFromRemote(owner.getLogin());

            NavDirections action = LoginFragmentDirections.actionLoginToUser();
            Navigation.findNavController(requireActivity(), R.id.loginButton).navigate(action);
         }
      });

      repositoriesViewModel.repositoriesLoadError.observe(requireActivity(), isError -> {
         if(isError != null && isError instanceof Boolean)
         {
//            listError.setVisibility(isError ? View.VISIBLE : View.GONE);
         }
      });

      repositoriesViewModel.loading.observe(requireActivity(), isLoading -> {
         if(isLoading != null & isLoading instanceof Boolean)
         {
//            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//            if(isLoading)
//            {
//               listError.setVisibility(View.GONE);
//               dogsList.setVisibility(View.GONE);
//            }
         }
      });
   }
}