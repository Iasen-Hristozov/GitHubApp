package test.githubapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class User
{
   @ColumnInfo(name = "login")
   @SerializedName("login")
   @Expose
   private String  login;

   @PrimaryKey
   @ColumnInfo(name = "id")
   @SerializedName("id")
   @Expose
   private Integer id;

   @ColumnInfo(name = "node_id")
   @SerializedName("node_id")
   @Expose
   private String  nodeId;

   @ColumnInfo(name = "avatar_url")
   @SerializedName("avatar_url")
   @Expose
   private String  avatarUrl;

   @ColumnInfo(name = "gravatar_id")
   @SerializedName("gravatar_id")
   @Expose
   private String  gravatarId;

   @ColumnInfo(name = "url")
   @SerializedName("url")
   @Expose
   private String  url;

   @ColumnInfo(name = "html_url")
   @SerializedName("html_url")
   @Expose
   private String  htmlUrl;

   @ColumnInfo(name = "followers_url")
   @SerializedName("followers_url")
   @Expose
   private String  followersUrl;

   @ColumnInfo(name = "following_url")
   @SerializedName("following_url")
   @Expose
   private String  followingUrl;

   @ColumnInfo(name = "gists_url")
   @SerializedName("gists_url")
   @Expose
   private String  gistsUrl;

   @ColumnInfo(name = "starred_url")
   @SerializedName("starred_url")
   @Expose
   private String  starredUrl;

   @ColumnInfo(name = "subscriptions_url")
   @SerializedName("subscriptions_url")
   @Expose
   private String  subscriptionsUrl;

   @ColumnInfo(name = "organizations_url")
   @SerializedName("organizations_url")
   @Expose
   private String  organizationsUrl;

   @ColumnInfo(name = "repos_url")
   @SerializedName("repos_url")
   @Expose
   private String  reposUrl;

   @ColumnInfo(name = "events_url")
   @SerializedName("events_url")
   @Expose
   private String  eventsUrl;

   @ColumnInfo(name = "receivedEventsUrl")
   @SerializedName("received_events_url")
   @Expose
   private String  receivedEventsUrl;

   @ColumnInfo(name = "type")
   @SerializedName("type")
   @Expose
   private String  type;

   @ColumnInfo(name = "site_admin")
   @SerializedName("site_admin")
   @Expose
   private Boolean siteAdmin;

   public String getLogin()
   {
      return login;
   }

   public void setLogin(String login)
   {
      this.login = login;
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getNodeId()
   {
      return nodeId;
   }

   public void setNodeId(String nodeId)
   {
      this.nodeId = nodeId;
   }

   public String getAvatarUrl()
   {
      return avatarUrl;
   }

   public void setAvatarUrl(String avatarUrl)
   {
      this.avatarUrl = avatarUrl;
   }

   public String getGravatarId()
   {
      return gravatarId;
   }

   public void setGravatarId(String gravatarId)
   {
      this.gravatarId = gravatarId;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public String getHtmlUrl()
   {
      return htmlUrl;
   }

   public void setHtmlUrl(String htmlUrl)
   {
      this.htmlUrl = htmlUrl;
   }

   public String getFollowersUrl()
   {
      return followersUrl;
   }

   public void setFollowersUrl(String followersUrl)
   {
      this.followersUrl = followersUrl;
   }

   public String getFollowingUrl()
   {
      return followingUrl;
   }

   public void setFollowingUrl(String followingUrl)
   {
      this.followingUrl = followingUrl;
   }

   public String getGistsUrl()
   {
      return gistsUrl;
   }

   public void setGistsUrl(String gistsUrl)
   {
      this.gistsUrl = gistsUrl;
   }

   public String getStarredUrl()
   {
      return starredUrl;
   }

   public void setStarredUrl(String starredUrl)
   {
      this.starredUrl = starredUrl;
   }

   public String getSubscriptionsUrl()
   {
      return subscriptionsUrl;
   }

   public void setSubscriptionsUrl(String subscriptionsUrl)
   {
      this.subscriptionsUrl = subscriptionsUrl;
   }

   public String getOrganizationsUrl()
   {
      return organizationsUrl;
   }

   public void setOrganizationsUrl(String organizationsUrl)
   {
      this.organizationsUrl = organizationsUrl;
   }

   public String getReposUrl()
   {
      return reposUrl;
   }

   public void setReposUrl(String reposUrl)
   {
      this.reposUrl = reposUrl;
   }

   public String getEventsUrl()
   {
      return eventsUrl;
   }

   public void setEventsUrl(String eventsUrl)
   {
      this.eventsUrl = eventsUrl;
   }

   public String getReceivedEventsUrl()
   {
      return receivedEventsUrl;
   }

   public void setReceivedEventsUrl(String receivedEventsUrl)
   {
      this.receivedEventsUrl = receivedEventsUrl;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public Boolean getSiteAdmin()
   {
      return siteAdmin;
   }

   public void setSiteAdmin(Boolean siteAdmin)
   {
      this.siteAdmin = siteAdmin;
   }

}
