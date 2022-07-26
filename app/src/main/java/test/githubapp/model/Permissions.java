package test.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "permissions")
public class Permissions
{
   @PrimaryKey
   @ColumnInfo(name = "repository_id")
   private int repositoryId;

   @ColumnInfo(name = "admin")
   @SerializedName("admin")
   @Expose
   private Boolean admin;

   @ColumnInfo(name = "maintain")
   @SerializedName("maintain")
   @Expose
   private Boolean maintain;

   @ColumnInfo(name = "push")
   @SerializedName("push")
   @Expose
   private Boolean push;

   @ColumnInfo(name = "triage")
   @SerializedName("triage")
   @Expose
   private Boolean triage;

   @ColumnInfo(name = "pull")
   @SerializedName("pull")
   @Expose
   private Boolean pull;

   public Boolean getAdmin()
   {
      return admin;
   }

   public void setAdmin(Boolean admin)
   {
      this.admin = admin;
   }

   public Boolean getMaintain()
   {
      return maintain;
   }

   public void setMaintain(Boolean maintain)
   {
      this.maintain = maintain;
   }

   public Boolean getPush()
   {
      return push;
   }

   public void setPush(Boolean push)
   {
      this.push = push;
   }

   public Boolean getTriage()
   {
      return triage;
   }

   public void setTriage(Boolean triage)
   {
      this.triage = triage;
   }

   public Boolean getPull()
   {
      return pull;
   }

   public void setPull(Boolean pull)
   {
      this.pull = pull;
   }

   public int getRepositoryId()
   {
      return repositoryId;
   }

   public void setRepositoryId(int repositoryId)
   {
      this.repositoryId = repositoryId;
   }
}
