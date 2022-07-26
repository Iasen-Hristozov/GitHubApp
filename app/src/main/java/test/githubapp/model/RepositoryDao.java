package test.githubapp.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RepositoryDao
{
   @Insert
   List<Long> insertAll(Repository... repositories);

   @Query("SELECT * FROM repositories")
   List<Repository> getAllRepositories();

   @Query("SELECT * FROM repositories WHERE id = :id")
   Repository getRepository(int id);

   @Query("DELETE FROM repositories")
   void deleteAllRepositories();
}
