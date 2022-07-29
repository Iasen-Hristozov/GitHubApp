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
   List<User> getAllUsers();

   @Query("SELECT * FROM users WHERE id = :id")
   User getUser(int id);

   @Query("DELETE FROM users")
   void deleteAllUsers();
}
