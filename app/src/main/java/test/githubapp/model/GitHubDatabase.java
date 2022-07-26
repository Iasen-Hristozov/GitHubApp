package test.githubapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.DeleteColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

@Database(entities = {Repository.class, User.class, Permissions.class}, version = 1, exportSchema = true)

//@Database(entities = {Repository.class}, version = 2, exportSchema = true, autoMigrations = {
//      @AutoMigration(from = 1, to = 2, spec = GitHubDatabase.MyAutoMigration.class)
//})

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

   @DeleteColumn(tableName = "repositories", columnName = "name")
   static class MyAutoMigration implements AutoMigrationSpec
   { }

   public abstract RepositoryDao repositoryDao();

   public abstract UsersDao ownerDao();

   public abstract PermissionsDao permissionsDao();
}
