package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import test.githubapp.R;

import androidx.fragment.app.Fragment;
import test.githubapp.viewmodel.RepositoryViewModel;

public class RepositoryFragment extends Fragment
{
   private boolean isStarred = false;
   public RepositoryFragment()
   {
      // Required empty public constructor
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
   }

   @Override
   public View onCreateView(LayoutInflater inflater,
                            ViewGroup container,
                            Bundle savedInstanceState)
   {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_repository, container, false);
      TextView repositoryTextView = view.findViewById(R.id.repositoryTextView);
      TextView contributorsTextView = view.findViewById(R.id.contributorsTextView);
      TextView ownerTextView = view.findViewById(R.id.ownerTextView);
      ImageView starredImage = view.findViewById(R.id.staredImageView);
      starredImage.setOnClickListener(v -> {
         isStarred = !isStarred;
         ((ImageView)v).setImageResource(isStarred
                                         ? android.R.drawable.btn_star_big_off
                                         : android.R.drawable.btn_star_big_on);
      });

      RepositoryViewModel repositoryViewModel = new ViewModelProvider(requireActivity()).get(RepositoryViewModel.class);
      repositoryViewModel.repositoryLiveData.observe(requireActivity(), repository -> {
         repositoryTextView.setText(repository.getName());
         ownerTextView.setText(repository.getOwner().getLogin());
      });
      repositoryViewModel.contributorsLiveData.observe(requireActivity(), contributors -> {
         contributorsTextView.setText("Contributors: " + contributors.size());
      });

      return view;
   }
}