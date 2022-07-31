package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import test.githubapp.R;
import test.githubapp.util.SharedPreferencesHelper;
import test.githubapp.viewmodel.OwnerViewModel;

public class LoginFragment extends Fragment
{
//   private RepositoriesViewModel repositoriesViewModel;
   private OwnerViewModel ownerViewModel;

   private EditText tokenEditText;
   private TextView loginError;
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
//      if (view != null) {
//         return view;
//      }
      View view = inflater.inflate(R.layout.fragment_login, container, false);

      tokenEditText = view.findViewById(R.id.tokenEditText);
      Button loginButton = view.findViewById(R.id.loginButton);
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
      loginError = view.findViewById(R.id.loginError);

      return view;
   }

   private void getUserInfo()
   {
      ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);
      ownerViewModel.refresh();

      observeViewModel();
   }

   private void observeViewModel()
   {
      ownerViewModel.ownerLiveData.observe(requireActivity(), owner -> {
         if(owner != null)
         {
            LoginFragmentDirections.ActionLoginToUser action = LoginFragmentDirections.actionLoginToUser();
            action.setOwner(owner.getLogin());
//            NavController navController = Navigation.findNavController(requireView());


            Navigation.findNavController(requireView()).navigate(action);
//            Navigation.findNavController(requireActivity(), R.id.fragment).navigate(action);

//
//            navController.navigate(action);
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
            progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
         }
      });

   }
}