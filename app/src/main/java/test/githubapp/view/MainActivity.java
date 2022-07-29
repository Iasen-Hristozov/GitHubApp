package test.githubapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import test.githubapp.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      NavController navController = Navigation.findNavController(this, R.id.fragment);
      NavigationUI.setupActionBarWithNavController(this, navController);
   }
}