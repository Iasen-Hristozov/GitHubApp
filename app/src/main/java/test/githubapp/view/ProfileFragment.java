package test.githubapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import test.githubapp.R;

public class ProfileFragment extends Fragment
{
   public ProfileFragment()
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
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_profile, container, false);

      view.findViewById(R.id.logoutButton).setOnClickListener(v -> {
         Navigation.findNavController(v).navigate(R.id.action_to_login);
      });

      return view;
   }
}