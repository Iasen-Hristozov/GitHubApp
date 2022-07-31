package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import test.githubapp.R;
import test.githubapp.viewmodel.RepositoryViewModel;
import test.githubapp.viewmodel.UsersViewModel;

public class RepositoryFragment extends Fragment implements MenuProvider
{
   private boolean isStarred = false;

   private RepositoryViewModel repositoryViewModel;

   private FrameLayout progressView;

   public RepositoryFragment()
   {
      // Required empty public constructor
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      requireActivity().addMenuProvider(this);
   }

   @Override
   public View onCreateView(LayoutInflater inflater,
                            ViewGroup container,
                            Bundle savedInstanceState)
   {
      View view = inflater.inflate(R.layout.fragment_repository, container, false);
      TextView repositoryTextView = view.findViewById(R.id.repositoryTextView);
      UsersViewModel usersViewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);
      TextView contributorsTextView = view.findViewById(R.id.contributorsTextView);
      contributorsTextView.setOnClickListener(v -> {
         repositoryViewModel.contributorsLiveData.observe(requireActivity(), usersViewModel::setUsersLiveData);
         Navigation.findNavController(v).navigate(R.id.action_repository_to_users);
      });
      TextView ownerTextView = view.findViewById(R.id.ownerTextView);
      ImageView starredImage = view.findViewById(R.id.staredImageView);

      progressView = view.findViewById(R.id.progressView);
      starredImage.setOnClickListener(v -> {
         isStarred = !isStarred;
         ((ImageView)v).setImageResource(isStarred
                                         ? android.R.drawable.btn_star_big_off
                                         : android.R.drawable.btn_star_big_on);
      });

      repositoryViewModel = new ViewModelProvider(requireActivity()).get(RepositoryViewModel.class);
      repositoryViewModel.repositoryLiveData.observe(requireActivity(), repository -> {
         repositoryTextView.setText(repository.getName());
         ownerTextView.setText(repository.getOwner().getLogin());

         repositoryViewModel.fetchContributorsFromRemote(repository.getOwner().getLogin(), repository.getName());
      });
      repositoryViewModel.contributorsLiveData.observe(requireActivity(), contributors -> {
         contributorsTextView.setText(String.valueOf(contributors.size()));
      });
      repositoryViewModel.loading.observe(requireActivity(), isLoading -> {
         progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
      });

      return view;
   }

   @Override
   public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater)
   {
      menuInflater.inflate(R.menu.menu_repository, menu);
   }

   @Override
   public boolean onMenuItemSelected(@NonNull MenuItem menuItem)
   {
//      return NavigationUI.onNavDestinationSelected(menuItem, Navigation.findNavController(requireActivity(), R.id.fragment));
      switch(menuItem.getItemId())
      {
         case R.id.fragment_profile:
            Navigation.findNavController(requireView()).navigate(R.id.action_to_profile);
            break;

         case R.id.fragment_login:
            Navigation.findNavController(requireView()).navigate(R.id.action_to_login);
            break;


         default:
            break;

      }
      return super.onContextItemSelected(menuItem);
   }

   @Override
   public void onPause()
   {
      super.onPause();
      requireActivity().removeMenuProvider(this);

   }
}