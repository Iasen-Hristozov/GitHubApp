package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import test.githubapp.viewmodel.UsersViewModel;

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
   private FrameLayout progressView;

   public UserFragment()
   {
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
      progressView = view.findViewById(R.id.progressView);

      repositoriesViewModel = new ViewModelProvider(requireActivity()).get(RepositoriesViewModel.class);
      followersViewModel = new ViewModelProvider(requireActivity()).get(FollowersViewModel.class);
      followingViewModel = new ViewModelProvider(requireActivity()).get(FollowingViewModel.class);
      ownerViewModel = new ViewModelProvider(requireActivity()).get(OwnerViewModel.class);
      UsersViewModel usersViewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);

      followersTextView.setOnClickListener( v -> {
         followersViewModel.followersLiveData.observe(requireActivity(), usersViewModel::setUsersLiveData);
            Navigation.findNavController(v).navigate(action);
      });
      followingTextView.setOnClickListener( v -> {
         followingViewModel.followingLiveData.observe(requireActivity(), usersViewModel::setUsersLiveData);
         Navigation.findNavController(v).navigate(action);
      });

      getUserData();

      observeViewModels();

      return view;
   }

   private void getUserData()
   {
      repositoriesViewModel.refresh();
      followersViewModel.fetchFromRemote(owner);
      followingViewModel.fetchFromRemote(owner);
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
            showProgressView();
      });

      ownerViewModel.ownerLiveData.observe(requireActivity(), owner ->
      {
         loginTextView.setText(owner.getLogin());
         Utils.loadImage(avatarImageView, owner.getAvatarUrl(), Utils.getProgressDrawable(requireActivity()));
      });

      followersViewModel.followersLiveData.observe(requireActivity(), followers -> {
         if(followers != null)
            followersTextView.setText(String.valueOf(followers.size()));
      });

      followersViewModel.loading.observe(requireActivity(), isLoading -> {
         if(isLoading != null)
            showProgressView();
      });

      followingViewModel.followingLiveData.observe(requireActivity(), following -> {
         if(following != null)
            followingTextView.setText(String.valueOf(following.size()));
      });

      followingViewModel.loading.observe(requireActivity(), isLoading -> {
         if(isLoading != null)
            showProgressView();
      });
   }

   private void showProgressView()
   {
         progressView.setVisibility(Boolean.TRUE.equals(repositoriesViewModel.loading.getValue())
                                          || Boolean.TRUE.equals(followersViewModel.loading.getValue())
                                          || Boolean.TRUE.equals(followingViewModel.loading.getValue()) ? View.VISIBLE : View.GONE);
   }

}