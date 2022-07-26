package test.githubapp.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PermissionsDao
{
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insert(Permissions permissions);

   @Query("SELECT * FROM permissions WHERE repository_id = :repositoryId")
   Permissions getPermissions(int repositoryId);

   @Query("DELETE FROM permissions")
   void deleteAllPermissions();
}
