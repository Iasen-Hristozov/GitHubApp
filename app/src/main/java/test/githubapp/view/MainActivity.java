package test.githubapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import test.githubapp.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
   private NavController navController;
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      navController = Navigation.findNavController(this, R.id.fragment);
      NavigationUI.setupActionBarWithNavController(this, navController);
   }

   @Override
   public boolean onSupportNavigateUp()
   {
//      return navController.popBackStack();
//      return navController.navigateBack(navController, (DrawerLayout) null);
      return NavigationUI.navigateUp(navController, (DrawerLayout) null);
   }
}