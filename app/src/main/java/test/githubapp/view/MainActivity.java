package test.githubapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import test.githubapp.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
   NavController navController;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      navController = Navigation.findNavController(this, R.id.fragment);
      NavigationUI.setupActionBarWithNavController(this, navController);
   }
}