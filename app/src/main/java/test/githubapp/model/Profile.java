package test.githubapp.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

@Entity
public class Profile
{
   @Embedded
   public User user;

   @Relation(parentColumn = "id", entityColumn = "parent_id")
   public List<User> followers;

}
