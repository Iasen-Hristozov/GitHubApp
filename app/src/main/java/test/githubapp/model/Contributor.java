package test.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Contributor extends User
{
   @Expose
   private Boolean siteAdmin;
   @SerializedName("contributions")
   @Expose
   private Integer contributions;

   public Integer getContributions()
   {
      return contributions;
   }

   public void setContributions(Integer contributions)
   {
      this.contributions = contributions;
   }
}
