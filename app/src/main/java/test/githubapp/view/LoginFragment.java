package test.githubapp.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
//   private RepositoriesViewModel repositoriesViewModel;
   private OwnerViewModel ownerViewModel;

   private EditText tokenEditText;
   private Button loginButton;
   private TextView loginError;
   private ProgressBar loadingView;
   private FrameLayout progressView;


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

      tokenEditText = view.findViewById(R.id.tokenEditText);
      loginButton = view.findViewById(R.id.loginButton);
      loginButton.setOnClickListener(v -> {
         token = tokenEditText.getText().toString();
         if(token.isEmpty())
            tokenEditText.setError("You must enter a token");
         else
         {
            SharedPreferencesHelper.getInstance(getContext()).saveToken(token);
            getUserInfo();
         }
      });
      progressView = view.findViewById(R.id.progressView);
      progressView.setVisibility(View.GONE);


      loginError = view.findViewById(R.id.loginError);
      loginError.setVisibility(View.GONE);
//      loadingView = view.findViewById(R.id.loadingView);
//      loadingView.setVisibility(View.GONE);

      return view;
   }

   private void getUserInfo()
   {
//      repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
//      repositoriesViewModel.refresh();

      ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);
      ownerViewModel.refresh();

      observeViewModel();
   }

   private void observeViewModel()
   {
//      repositoriesViewModel.repositories.observe(requireActivity(), repositories -> {
//         if(repositories != null && repositories instanceof List)
//         {
//            User owner = repositories.get(0).getOwner();
//            OwnerViewModel ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);
//            ownerViewModel.setOwnerLiveData(owner);
//
//            FollowersViewModel followersViewModel = new ViewModelProvider(requireActivity()).get(FollowersViewModel.class);
//            followersViewModel.fetchFromRemote(owner.getLogin());
//
//            FollowingViewModel followingViewModel = new ViewModelProvider(requireActivity()).get(FollowingViewModel.class);
//            followingViewModel.fetchFromRemote(owner.getLogin());
//
//            NavDirections action = LoginFragmentDirections.actionLoginToUser();
//            Navigation.findNavController(loginButton).navigate(action);
//         }
//      });
//
//      repositoriesViewModel.repositoriesLoadError.observe(requireActivity(), isError -> {
//         if(isError != null)
//         {
//            loginError.setVisibility(isError ? View.VISIBLE : View.GONE);
//         }
//      });
//
//      repositoriesViewModel.loading.observe(requireActivity(), isLoading -> {
//         if(isLoading != null)
//         {
//            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//            if(isLoading)
//            {
//               loginButton.setVisibility(View.GONE);
//               tokenEditText.setVisibility(View.GONE);
//            }
//         }
//      });

      ownerViewModel.ownerLiveData.observe(requireActivity(), owner -> {
         if(owner != null)
         {
//            NavDirections action = LoginFragmentDirections.actionLoginToUser();
            LoginFragmentDirections.ActionLoginToUser action = LoginFragmentDirections.actionLoginToUser();
            action.setOwner(owner.getLogin());
            Navigation.findNavController(loginButton).navigate(action);
         }
      });

      ownerViewModel.ownerLoadError.observe(requireActivity(), isError -> {
         if(isError != null)
         {
            loginError.setVisibility(isError ? View.VISIBLE : View.GONE);
         }
      });

      ownerViewModel.loading.observe(requireActivity(), isLoading -> {
         if(isLoading != null)
         {
//            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//            loadingView.setIndeterminate(true);
            progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//            Dialog overlayDialog = new Dialog(requireActivity(), android.R.style.Theme_Panel); //display an invisible overlay dialog to prevent user interaction and pressing back
//            overlayDialog.setCancelable(false);
//            overlayDialog.show();
//            if(isLoading)
//            {
//               loginButton.setVisibility(View.GONE);
//               tokenEditText.setVisibility(View.GONE);
//            }
         }
      });

   }
}