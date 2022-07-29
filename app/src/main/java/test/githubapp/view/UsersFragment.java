package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import test.githubapp.R;

import androidx.fragment.app.Fragment;
import test.githubapp.viewmodel.UsersViewModel;

public class UsersFragment extends Fragment
{
   UsersListAdapter usersListAdapter;

   public UsersFragment()
   {
      // Required empty public constructor
   }

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setHasOptionsMenu(true);
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
   {
      View view = inflater.inflate(R.layout.fragment_users, container, false);

      RecyclerView usersRecycleView = view.findViewById(R.id.usersRecyclerView);

      UsersViewModel usersViewModel = new ViewModelProvider(requireActivity()).get(UsersViewModel.class);
      usersViewModel.usersLiveData.observe(requireActivity(), users -> {
         usersListAdapter = new UsersListAdapter(getActivity(), users);
         LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
         usersRecycleView.setLayoutManager(layoutManager);
         DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(usersRecycleView.getContext(),
                                                                                 layoutManager.getOrientation());
         usersRecycleView.addItemDecoration(dividerItemDecoration);
         usersRecycleView.setAdapter(usersListAdapter);
      });

      return view;
   }

   @Override
   public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      // TODO Add your menu entries here
      inflater.inflate(R.menu.menu_users, menu);
      final MenuItem searchItem = menu.findItem(R.id.action_search).setVisible(true);
      super.onCreateOptionsMenu(menu, inflater);

      final SearchView searchView = (SearchView) searchItem.getActionView();

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
      {

         @Override
         public boolean onQueryTextSubmit(String arg0)
         {
            return false;
         }

         @Override
         public boolean onQueryTextChange(String arg0)
         {
            usersListAdapter.filter(arg0);
            return true;
         }
      });
   }
}