package test.githubapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Repository.class, User.class, Permissions.class}, version = 1, exportSchema = true)

public abstract class GitHubDatabase extends RoomDatabase
{
   private static GitHubDatabase instance;

   public static GitHubDatabase getInstance(Context context)
   {
      if(instance == null)
         instance = Room.databaseBuilder(context.getApplicationContext(),
                                         GitHubDatabase.class,
                                         "githubdatabase")
         .build();
      return instance;
   }

   public abstract RepositoryDao repositoriesDao();

   public abstract UsersDao usersDao();

   public abstract PermissionsDao permissionsDao();
}
