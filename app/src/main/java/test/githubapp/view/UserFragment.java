package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import test.githubapp.R;

import androidx.fragment.app.Fragment;
import test.githubapp.util.Utils;
import test.githubapp.viewmodel.FollowersViewModel;
import test.githubapp.viewmodel.FollowingViewModel;
import test.githubapp.viewmodel.OwnerViewModel;
import test.githubapp.viewmodel.RepositoriesViewModel;
import test.githubapp.viewmodel.RepositoryViewModel;

public class UserFragment extends Fragment
{
   private String owner;

   private RepositoriesViewModel repositoriesViewModel;
   private FollowersViewModel followersViewModel;
   private FollowingViewModel followingViewModel;
   private OwnerViewModel ownerViewModel;

   private TextView loginTextView;
   private ImageView avatarImageView;
   private TextView followersTextView;
   private TextView followingTextView;
   private RecyclerView repositoriesRecycleView;

   public UserFragment()
   {
      // Required empty public constructor
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      View view = inflater.inflate(R.layout.fragment_user, container, false);

      if(getArguments() != null)
      {
         owner = UserFragmentArgs.fromBundle(getArguments())
                                     .getOwner();
      }

      NavDirections action = UserFragmentDirections.actionUserToUsers();

      loginTextView = view.findViewById(R.id.loginTextView);
      avatarImageView = view.findViewById(R.id.avatarImageView);
      followersTextView = view.findViewById(R.id.followersTextView);
      followingTextView = view.findViewById(R.id.followingTextView);
      repositoriesRecycleView = view.findViewById(R.id.repositoriesRecycleView);

      repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
      repositoriesViewModel.refresh();

      followersViewModel = new ViewModelProvider(requireActivity()).get(FollowersViewModel.class);
      followingViewModel = new ViewModelProvider(requireActivity()).get(FollowingViewModel.class);
      ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);

      followersTextView.setOnClickListener( v -> {
//         UsersViewModel usersViewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);
//         followersViewModel.followersLiveData.observe(requireActivity(), followers -> {
//            usersViewModel.setUsersLiveData(followers);
//         });
         Navigation.findNavController(v).navigate(action);
      });
      followingTextView.setOnClickListener( v -> {
//         UsersViewModel usersViewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);
//         followingViewModel.followingLiveData.observe(requireActivity(), following -> {
//            usersViewModel.setUsersLiveData(following);
//         });

         Navigation.findNavController(v).navigate(action);
      });



//      RepositoriesViewModel repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
//      repositoriesViewModel.repositories.observe(requireActivity(), repositories -> {
//         RepositoryViewModel repositoryViewModel = new ViewModelProvider(requireActivity()).get(RepositoryViewModel.class);
//         RepositoriesListAdapter repositoriesListAdapter = new RepositoriesListAdapter(repositories, repositoryViewModel);
//
//         LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(repositoriesRecycleView.getContext(),
//                                                                                 layoutManager.getOrientation());
//         repositoriesRecycleView.addItemDecoration(dividerItemDecoration);
//         repositoriesRecycleView.setLayoutManager(layoutManager);
//         repositoriesRecycleView.setAdapter(repositoriesListAdapter);
//      });

      getUserData();

      observeViewModels();

      return view;
   }

   private void getUserData()
   {
      repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
      repositoriesViewModel.refresh();
   }

   private void observeViewModels()
   {
      repositoriesViewModel.repositories.observe(requireActivity(), repositories -> {
         if(repositories != null)
         {
            RepositoryViewModel repositoryViewModel = new ViewModelProvider(requireActivity()).get(RepositoryViewModel.class);
            RepositoriesListAdapter repositoriesListAdapter = new RepositoriesListAdapter(repositories, repositoryViewModel);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(repositoriesRecycleView.getContext(),
                                                                                    layoutManager.getOrientation());
            repositoriesRecycleView.addItemDecoration(dividerItemDecoration);
            repositoriesRecycleView.setLayoutManager(layoutManager);
            repositoriesRecycleView.setAdapter(repositoriesListAdapter);



//            FollowersViewModel followersViewModel = new ViewModelProvider(requireActivity()).get(FollowersViewModel.class);
            followersViewModel.fetchFromRemote(owner);

//            FollowingViewModel followingViewModel = new ViewModelProvider(requireActivity()).get(FollowingViewModel.class);
            followingViewModel.fetchFromRemote(owner);
         }
      });

      repositoriesViewModel.repositoriesLoadError.observe(requireActivity(), isError -> {
         if(isError != null)
         {
//            loginError.setVisibility(isError ? View.VISIBLE : View.GONE);
         }
      });

      repositoriesViewModel.loading.observe(requireActivity(), isLoading -> {
         if(isLoading != null)
         {
//            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//            if(isLoading)
//            {
//               loginButton.setVisibility(View.GONE);
//               tokenEditText.setVisibility(View.GONE);
//            }
         }
      });

      ownerViewModel.ownerLiveData.observe(requireActivity(), owner ->
      {
         loginTextView.setText(owner.getLogin());
         Utils.loadImage(avatarImageView, owner.getAvatarUrl(), Utils.getProgressDrawable(requireActivity()));
      });

      followersViewModel.followersLiveData.observe(requireActivity(), followers -> {
         followersTextView.setText(String.valueOf(followers.size()));
      });

      followingViewModel.followingLiveData.observe(requireActivity(), following -> {
         followingTextView.setText(String.valueOf(following.size()));
      });

   }

}