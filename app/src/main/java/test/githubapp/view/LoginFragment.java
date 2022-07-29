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
import test.githubapp.viewmodel.ContributorsViewModel;
import test.githubapp.viewmodel.FollowersViewModel;
import test.githubapp.viewmodel.FollowingViewModel;
import test.githubapp.viewmodel.OwnerViewModel;
import test.githubapp.viewmodel.RepositoriesViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment
{
   private RepositoriesViewModel repositoriesViewModel;



   // TODO: Rename parameter arguments, choose names that match
   // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";

   // TODO: Rename and change types of parameters
   private String mParam1;
   private String mParam2;

   public LoginFragment()
   {
      // Required empty public constructor
   }

   /**
    * Use this factory method to create a new instance of
    * this fragment using the provided parameters.
    *
    * @param param1 Parameter 1.
    * @param param2 Parameter 2.
    * @return A new instance of fragment LoginFragment.
    */
   // TODO: Rename and change types and number of parameters
   public static LoginFragment newInstance(String param1, String param2)
   {
      LoginFragment fragment = new LoginFragment();
      Bundle args = new Bundle();
      args.putString(ARG_PARAM1, param1);
      args.putString(ARG_PARAM2, param2);
      fragment.setArguments(args);
      return fragment;
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      if(getArguments() != null)
      {
         mParam1 = getArguments().getString(ARG_PARAM1);
         mParam2 = getArguments().getString(ARG_PARAM2);
      }


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
            getUserInfo(token);
      });

//      repositoriesViewModel = ViewModelProviders.of(requireActivity())
//                                                .get(RepositoriesViewModel.class);




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
//            LoginFragmentDirections.Act action = LoginFragmentDirections.
//            ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
//            action.setDogUuid(uuid);
//            Navigation.findNavController(v).navigate(action);
////            dogsList.setVisibility(View.VISIBLE);
////            dogsListAdapter.updateDogsList(dogs);
//
//      NavDirections action = LoginFragmentDirections.actionList();
//      Navigation.findNavController(fab).navigate(action);


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