package test.githubapp.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UsersDao
{
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   long insert(User user);

   @Query("SELECT * FROM users")
   List<Repository> getAllUsers();

   @Query("SELECT * FROM users WHERE id = :id")
   Repository getUser(int id);

   @Query("DELETE FROM users")
   void deleteAllUsers();
}
