package test.githubapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import test.githubapp.R;
import test.githubapp.model.Repository;
import test.githubapp.viewmodel.RepositoryViewModel;

public class RepositoriesListAdapter extends RecyclerView.Adapter<RepositoriesListAdapter.RepositoryViewHolder>
{
   private final List<Repository> repositoriesList;
   private final RepositoryViewModel repositoryViewModel;

   public RepositoriesListAdapter(List<Repository> repositoriesList, RepositoryViewModel repositoryViewModel)
   {
      this.repositoriesList = repositoriesList;
      this.repositoryViewModel = repositoryViewModel;
   }

   @NonNull
   @Override
   public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
   {
      View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_repository, parent, false);

      return new RepositoryViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position)
   {
      holder.getRepositoryTextView().setText(repositoriesList.get(position).getName());
      holder.itemView.setOnClickListener(v -> {
               Repository repository = repositoriesList.get(position);
               repositoryViewModel.fetchContributorsFromRemote(repository.getOwner().getLogin(), repository.getName());
               repositoryViewModel.setRepositoryLiveData(repository);
               Navigation.findNavController(v).navigate(R.id.action_user_to_repository);
      });
   }

   @Override
   public int getItemCount()
   {
      return repositoriesList.size();
   }


   static class RepositoryViewHolder extends RecyclerView.ViewHolder
   {
      private final TextView repositoryTextView;

      public RepositoryViewHolder(@NonNull View itemView)
      {
         super(itemView);
         repositoryTextView = (TextView) itemView.findViewById(R.id.repositoryTextView);
      }

      public TextView getRepositoryTextView()
      {
         return repositoryTextView;
      }
   }
}
