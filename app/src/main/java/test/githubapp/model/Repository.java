package test.githubapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "repositories")
public class Repository
{
   @PrimaryKey
   @ColumnInfo(name = "id")
   @SerializedName("id")
   @Expose
   private Integer id;

   @ColumnInfo(name = "node_id")
   @SerializedName("node_id")
   @Expose
   private String nodeId;

   @ColumnInfo(name = "name")
   @SerializedName("name")
   @Expose
   private String name;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @ColumnInfo(name = "full_name")
   @SerializedName("full_name")
   @Expose
   private String fullName;

   @ColumnInfo(name = "private")
   @SerializedName("private")
   @Expose
   private Boolean _private;

   @Ignore
//      @ColumnInfo(name = "owner")
//   @Embedded
   @SerializedName("owner")
   @Expose
   private User owner;

//   @Relation(
//         parentColumn = "id",
//         entityColumn = "owner_id"
//   )

   @ColumnInfo(name = "owner_id")
   private int ownerId;

   @ColumnInfo(name = "html_url")
   @SerializedName("html_url")
   @Expose
   private String      htmlUrl;

   @ColumnInfo(name = "description")
   @SerializedName("description")
   @Expose
   private String description;

   @ColumnInfo(name = "fork")
   @SerializedName("fork")
   @Expose
   private Boolean fork;

   @ColumnInfo(name = "url")
   @SerializedName("url")
   @Expose
   private String url;

   @ColumnInfo(name = "forks_url")
   @SerializedName("forks_url")
   @Expose
   private String forksUrl;

   @ColumnInfo(name = "keys_url")
   @SerializedName("keys_url")
   @Expose
   private String keysUrl;

   @ColumnInfo(name = "collaborators_url")
   @SerializedName("collaborators_url")
   @Expose
   private String collaboratorsUrl;

   @ColumnInfo(name = "teams_url")
   @SerializedName("teams_url")
   @Expose
   private String teamsUrl;

   @ColumnInfo(name = "hooks_url")
   @SerializedName("hooks_url")
   @Expose
   private String hooksUrl;

   @ColumnInfo(name = "issue_events_url")
   @SerializedName("issue_events_url")
   @Expose
   private String issueEventsUrl;

   @ColumnInfo(name = "events_url")
   @SerializedName("events_url")
   @Expose
   private String eventsUrl;

   @ColumnInfo(name = "assignees_url")
   @SerializedName("assignees_url")
   @Expose
   private String assigneesUrl;

   @ColumnInfo(name = "branches_url")
   @SerializedName("branches_url")
   @Expose
   private String branchesUrl;

   @ColumnInfo(name = "tags_url")
   @SerializedName("tags_url")
   @Expose
   private String tagsUrl;

   @ColumnInfo(name = "blobs_url")
   @SerializedName("blobs_url")
   @Expose
   private String blobsUrl;

   @ColumnInfo(name = "git_tags_url")
   @SerializedName("git_tags_url")
   @Expose
   private String gitTagsUrl;

   @ColumnInfo(name = "git_refs_url")
   @SerializedName("git_refs_url")
   @Expose
   private String gitRefsUrl;

   @ColumnInfo(name = "trees_url")
   @SerializedName("trees_url")
   @Expose
   private String treesUrl;

   @ColumnInfo(name = "statuses_url")
   @SerializedName("statuses_url")
   @Expose
   private String statusesUrl;

   @ColumnInfo(name = "languages_url")
   @SerializedName("languages_url")
   @Expose
   private String languagesUrl;

   @ColumnInfo(name = "stargazers_url")
   @SerializedName("stargazers_url")
   @Expose
   private String stargazersUrl;

   @ColumnInfo(name = "contributors_url")
   @SerializedName("contributors_url")
   @Expose
   private String contributorsUrl;

   @ColumnInfo(name = "subscribers_url")
   @SerializedName("subscribers_url")
   @Expose
   private String subscribersUrl;

   @ColumnInfo(name = "subscription_url")
   @SerializedName("subscription_url")
   @Expose
   private String subscriptionUrl;

   @ColumnInfo(name = "commits_url")
   @SerializedName("commits_url")
   @Expose
   private String commitsUrl;

   @ColumnInfo(name = "git_commits_url")
   @SerializedName("git_commits_url")
   @Expose
   private String gitCommitsUrl;

   @ColumnInfo(name = "comments_url")
   @SerializedName("comments_url")
   @Expose
   private String commentsUrl;

   @ColumnInfo(name = "issue_comment_url")
   @SerializedName("issue_comment_url")
   @Expose
   private String issueCommentUrl;

   @ColumnInfo(name = "contents_url")
   @SerializedName("contents_url")
   @Expose
   private String contentsUrl;

   @ColumnInfo(name = "compare_url")
   @SerializedName("compare_url")
   @Expose
   private String compareUrl;

   @ColumnInfo(name = "merges_url")
   @SerializedName("merges_url")
   @Expose
   private String mergesUrl;

   @ColumnInfo(name = "archive_url")
   @SerializedName("archive_url")
   @Expose
   private String archiveUrl;

   @ColumnInfo(name = "downloads_url")
   @SerializedName("downloads_url")
   @Expose
   private String downloadsUrl;

   @ColumnInfo(name = "issues_url")
   @SerializedName("issues_url")
   @Expose
   private String issuesUrl;

   @ColumnInfo(name = "pulls_url")
   @SerializedName("pulls_url")
   @Expose
   private String pullsUrl;

   @ColumnInfo(name = "milestones_url")
   @SerializedName("milestones_url")
   @Expose
   private String milestonesUrl;

   @ColumnInfo(name = "notifications_url")
   @SerializedName("notifications_url")
   @Expose
   private String notificationsUrl;

   @ColumnInfo(name = "labels_url")
   @SerializedName("labels_url")
   @Expose
   private String labelsUrl;

   @ColumnInfo(name = "releases_url")
   @SerializedName("releases_url")
   @Expose
   private String releasesUrl;

   @ColumnInfo(name = "deployments_url")
   @SerializedName("deployments_url")
   @Expose
   private String deploymentsUrl;

   @ColumnInfo(name = "created_at")
   @SerializedName("created_at")
   @Expose
   private String createdAt;

   @ColumnInfo(name = "updated_at")
   @SerializedName("updated_at")
   @Expose
   private String updatedAt;

   @ColumnInfo(name = "pushed_at")
   @SerializedName("pushed_at")
   @Expose
   private String pushedAt;

   @ColumnInfo(name = "git_url")
   @SerializedName("git_url")
   @Expose
   private String gitUrl;

   @ColumnInfo(name = "ssh_url")
   @SerializedName("ssh_url")
   @Expose
   private String sshUrl;

   @ColumnInfo(name = "clone_url")
   @SerializedName("clone_url")
   @Expose
   private String cloneUrl;

   @ColumnInfo(name = "svn_url")
   @SerializedName("svn_url")
   @Expose
   private String svnUrl;

   @ColumnInfo(name = "homepage")
   @SerializedName("homepage")
   @Expose
   private String homepage;

   @ColumnInfo(name = "size")
   @SerializedName("size")
   @Expose
   private Integer size;

   @ColumnInfo(name = "stargazers_count")
   @SerializedName("stargazers_count")
   @Expose
   private Integer stargazersCount;

   @ColumnInfo(name = "watchers_count")
   @SerializedName("watchers_count")
   @Expose
   private Integer watchersCount;

   @ColumnInfo(name = "language")
   @SerializedName("language")
   @Expose
   private String language;

   @ColumnInfo(name = "has_issues")
   @SerializedName("has_issues")
   @Expose
   private Boolean hasIssues;

   @ColumnInfo(name = "has_projects")
   @SerializedName("has_projects")
   @Expose
   private Boolean hasProjects;

   @ColumnInfo(name = "has_downloads")
   @SerializedName("has_downloads")
   @Expose
   private Boolean hasDownloads;

   @ColumnInfo(name = "has_wiki")
   @SerializedName("has_wiki")
   @Expose
   private Boolean hasWiki;

   @ColumnInfo(name = "has_pages")
   @SerializedName("has_pages")
   @Expose
   private Boolean hasPages;

   @ColumnInfo(name = "forks_count")
   @SerializedName("forks_count")
   @Expose
   private Integer forksCount;

   @ColumnInfo(name = "mirror_url")
   @SerializedName("mirror_url")
   @Expose
   private String mirrorUrl;

   @ColumnInfo(name = "archived")
   @SerializedName("archived")
   @Expose
   private Boolean archived;

   @ColumnInfo(name = "disabled")
   @SerializedName("disabled")
   @Expose
   private Boolean disabled;

   @ColumnInfo(name = "open_issues_count")
   @SerializedName("open_issues_count")
   @Expose
   private Integer openIssuesCount;

   @ColumnInfo(name = "license")
   @SerializedName("license")
   @Expose
   private String license;

   @ColumnInfo(name = "allow_forking")
   @SerializedName("allow_forking")
   @Expose
   private Boolean allowForking;

   @ColumnInfo(name = "is_template")
   @SerializedName("is_template")
   @Expose
   private Boolean isTemplate;

   @ColumnInfo(name = "web_commit_signoff_required")
   @SerializedName("web_commit_signoff_required")
   @Expose
   private Boolean webCommitSignoffRequired;

   @Ignore
//   @ColumnInfo(name = "topics")
   @SerializedName("topics")
   @Expose
   private List<String> topics = null;

   @ColumnInfo(name = "visibility")
   @SerializedName("visibility")
   @Expose
   private String visibility;

   @ColumnInfo(name = "forks")
   @SerializedName("forks")
   @Expose
   private Integer forks;

   @ColumnInfo(name = "open_issues")
   @SerializedName("open_issues")
   @Expose
   private Integer openIssues;

   @ColumnInfo(name = "watchers")
   @SerializedName("watchers")
   @Expose
   private Integer watchers;

   @ColumnInfo(name = "default_branch")
   @SerializedName("default_branch")
   @Expose
   private String defaultBranch;

   @Ignore
   @SerializedName("permissions")
   @Expose
   private Permissions permissions;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNodeId() {
      return nodeId;
   }

   public void setNodeId(String nodeId) {
      this.nodeId = nodeId;
   }



   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public Boolean getPrivate() {
      return _private;
   }

   public void setPrivate(Boolean _private) {
      this._private = _private;
   }

   public User getOwner() {
      return owner;
   }

   public void setOwner(User owner) {
      this.owner = owner;
   }

   public String getHtmlUrl() {
      return htmlUrl;
   }

   public void setHtmlUrl(String htmlUrl) {
      this.htmlUrl = htmlUrl;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Boolean getFork() {
      return fork;
   }

   public void setFork(Boolean fork) {
      this.fork = fork;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getForksUrl() {
      return forksUrl;
   }

   public void setForksUrl(String forksUrl) {
      this.forksUrl = forksUrl;
   }

   public String getKeysUrl() {
      return keysUrl;
   }

   public void setKeysUrl(String keysUrl) {
      this.keysUrl = keysUrl;
   }

   public String getCollaboratorsUrl() {
      return collaboratorsUrl;
   }

   public void setCollaboratorsUrl(String collaboratorsUrl) {
      this.collaboratorsUrl = collaboratorsUrl;
   }

   public String getTeamsUrl() {
      return teamsUrl;
   }

   public void setTeamsUrl(String teamsUrl) {
      this.teamsUrl = teamsUrl;
   }

   public String getHooksUrl() {
      return hooksUrl;
   }

   public void setHooksUrl(String hooksUrl) {
      this.hooksUrl = hooksUrl;
   }

   public String getIssueEventsUrl() {
      return issueEventsUrl;
   }

   public void setIssueEventsUrl(String issueEventsUrl) {
      this.issueEventsUrl = issueEventsUrl;
   }

   public String getEventsUrl() {
      return eventsUrl;
   }

   public void setEventsUrl(String eventsUrl) {
      this.eventsUrl = eventsUrl;
   }

   public String getAssigneesUrl() {
      return assigneesUrl;
   }

   public void setAssigneesUrl(String assigneesUrl) {
      this.assigneesUrl = assigneesUrl;
   }

   public String getBranchesUrl() {
      return branchesUrl;
   }

   public void setBranchesUrl(String branchesUrl) {
      this.branchesUrl = branchesUrl;
   }

   public String getTagsUrl() {
      return tagsUrl;
   }

   public void setTagsUrl(String tagsUrl) {
      this.tagsUrl = tagsUrl;
   }

   public String getBlobsUrl() {
      return blobsUrl;
   }

   public void setBlobsUrl(String blobsUrl) {
      this.blobsUrl = blobsUrl;
   }

   public String getGitTagsUrl() {
      return gitTagsUrl;
   }

   public void setGitTagsUrl(String gitTagsUrl) {
      this.gitTagsUrl = gitTagsUrl;
   }

   public String getGitRefsUrl() {
      return gitRefsUrl;
   }

   public void setGitRefsUrl(String gitRefsUrl) {
      this.gitRefsUrl = gitRefsUrl;
   }

   public String getTreesUrl() {
      return treesUrl;
   }

   public void setTreesUrl(String treesUrl) {
      this.treesUrl = treesUrl;
   }

   public String getStatusesUrl() {
      return statusesUrl;
   }

   public void setStatusesUrl(String statusesUrl) {
      this.statusesUrl = statusesUrl;
   }

   public String getLanguagesUrl() {
      return languagesUrl;
   }

   public void setLanguagesUrl(String languagesUrl) {
      this.languagesUrl = languagesUrl;
   }

   public String getStargazersUrl() {
      return stargazersUrl;
   }

   public void setStargazersUrl(String stargazersUrl) {
      this.stargazersUrl = stargazersUrl;
   }

   public String getContributorsUrl() {
      return contributorsUrl;
   }

   public void setContributorsUrl(String contributorsUrl) {
      this.contributorsUrl = contributorsUrl;
   }

   public String getSubscribersUrl() {
      return subscribersUrl;
   }

   public void setSubscribersUrl(String subscribersUrl) {
      this.subscribersUrl = subscribersUrl;
   }

   public String getSubscriptionUrl() {
      return subscriptionUrl;
   }

   public void setSubscriptionUrl(String subscriptionUrl) {
      this.subscriptionUrl = subscriptionUrl;
   }

   public String getCommitsUrl() {
      return commitsUrl;
   }

   public void setCommitsUrl(String commitsUrl) {
      this.commitsUrl = commitsUrl;
   }

   public String getGitCommitsUrl() {
      return gitCommitsUrl;
   }

   public void setGitCommitsUrl(String gitCommitsUrl) {
      this.gitCommitsUrl = gitCommitsUrl;
   }

   public String getCommentsUrl() {
      return commentsUrl;
   }

   public void setCommentsUrl(String commentsUrl) {
      this.commentsUrl = commentsUrl;
   }

   public String getIssueCommentUrl() {
      return issueCommentUrl;
   }

   public void setIssueCommentUrl(String issueCommentUrl) {
      this.issueCommentUrl = issueCommentUrl;
   }

   public String getContentsUrl() {
      return contentsUrl;
   }

   public void setContentsUrl(String contentsUrl) {
      this.contentsUrl = contentsUrl;
   }

   public String getCompareUrl() {
      return compareUrl;
   }

   public void setCompareUrl(String compareUrl) {
      this.compareUrl = compareUrl;
   }

   public String getMergesUrl() {
      return mergesUrl;
   }

   public void setMergesUrl(String mergesUrl) {
      this.mergesUrl = mergesUrl;
   }

   public String getArchiveUrl() {
      return archiveUrl;
   }

   public void setArchiveUrl(String archiveUrl) {
      this.archiveUrl = archiveUrl;
   }

   public String getDownloadsUrl() {
      return downloadsUrl;
   }

   public void setDownloadsUrl(String downloadsUrl) {
      this.downloadsUrl = downloadsUrl;
   }

   public String getIssuesUrl() {
      return issuesUrl;
   }

   public void setIssuesUrl(String issuesUrl) {
      this.issuesUrl = issuesUrl;
   }

   public String getPullsUrl() {
      return pullsUrl;
   }

   public void setPullsUrl(String pullsUrl) {
      this.pullsUrl = pullsUrl;
   }

   public String getMilestonesUrl() {
      return milestonesUrl;
   }

   public void setMilestonesUrl(String milestonesUrl) {
      this.milestonesUrl = milestonesUrl;
   }

   public String getNotificationsUrl() {
      return notificationsUrl;
   }

   public void setNotificationsUrl(String notificationsUrl) {
      this.notificationsUrl = notificationsUrl;
   }

   public String getLabelsUrl() {
      return labelsUrl;
   }

   public void setLabelsUrl(String labelsUrl) {
      this.labelsUrl = labelsUrl;
   }

   public String getReleasesUrl() {
      return releasesUrl;
   }

   public void setReleasesUrl(String releasesUrl) {
      this.releasesUrl = releasesUrl;
   }

   public String getDeploymentsUrl() {
      return deploymentsUrl;
   }

   public void setDeploymentsUrl(String deploymentsUrl) {
      this.deploymentsUrl = deploymentsUrl;
   }

   public String getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
   }

   public String getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
   }

   public String getPushedAt() {
      return pushedAt;
   }

   public void setPushedAt(String pushedAt) {
      this.pushedAt = pushedAt;
   }

   public String getGitUrl() {
      return gitUrl;
   }

   public void setGitUrl(String gitUrl) {
      this.gitUrl = gitUrl;
   }

   public String getSshUrl() {
      return sshUrl;
   }

   public void setSshUrl(String sshUrl) {
      this.sshUrl = sshUrl;
   }

   public String getCloneUrl() {
      return cloneUrl;
   }

   public void setCloneUrl(String cloneUrl) {
      this.cloneUrl = cloneUrl;
   }

   public String getSvnUrl() {
      return svnUrl;
   }

   public void setSvnUrl(String svnUrl) {
      this.svnUrl = svnUrl;
   }

   public String getHomepage() {
      return homepage;
   }

   public void setHomepage(String homepage) {
      this.homepage = homepage;
   }

   public Integer getSize() {
      return size;
   }

   public void setSize(Integer size) {
      this.size = size;
   }

   public Integer getStargazersCount() {
      return stargazersCount;
   }

   public void setStargazersCount(Integer stargazersCount) {
      this.stargazersCount = stargazersCount;
   }

   public Integer getWatchersCount() {
      return watchersCount;
   }

   public void setWatchersCount(Integer watchersCount) {
      this.watchersCount = watchersCount;
   }

   public String getLanguage() {
      return language;
   }

   public void setLanguage(String language) {
      this.language = language;
   }

   public Boolean getHasIssues() {
      return hasIssues;
   }

   public void setHasIssues(Boolean hasIssues) {
      this.hasIssues = hasIssues;
   }

   public Boolean getHasProjects() {
      return hasProjects;
   }

   public void setHasProjects(Boolean hasProjects) {
      this.hasProjects = hasProjects;
   }

   public Boolean getHasDownloads() {
      return hasDownloads;
   }

   public void setHasDownloads(Boolean hasDownloads) {
      this.hasDownloads = hasDownloads;
   }

   public Boolean getHasWiki() {
      return hasWiki;
   }

   public void setHasWiki(Boolean hasWiki) {
      this.hasWiki = hasWiki;
   }

   public Boolean getHasPages() {
      return hasPages;
   }

   public void setHasPages(Boolean hasPages) {
      this.hasPages = hasPages;
   }

   public Integer getForksCount() {
      return forksCount;
   }

   public void setForksCount(Integer forksCount) {
      this.forksCount = forksCount;
   }

   public String getMirrorUrl() {
      return mirrorUrl;
   }

   public void setMirrorUrl(String mirrorUrl) {
      this.mirrorUrl = mirrorUrl;
   }

   public Boolean getArchived() {
      return archived;
   }

   public void setArchived(Boolean archived) {
      this.archived = archived;
   }

   public Boolean getDisabled() {
      return disabled;
   }

   public void setDisabled(Boolean disabled) {
      this.disabled = disabled;
   }

   public Integer getOpenIssuesCount() {
      return openIssuesCount;
   }

   public void setOpenIssuesCount(Integer openIssuesCount) {
      this.openIssuesCount = openIssuesCount;
   }

   public String getLicense() {
      return license;
   }

   public void setLicense(String license) {
      this.license = license;
   }

   public Boolean getAllowForking() {
      return allowForking;
   }

   public void setAllowForking(Boolean allowForking) {
      this.allowForking = allowForking;
   }

   public Boolean getIsTemplate() {
      return isTemplate;
   }

   public void setIsTemplate(Boolean isTemplate) {
      this.isTemplate = isTemplate;
   }

   public Boolean getWebCommitSignoffRequired() {
      return webCommitSignoffRequired;
   }

   public void setWebCommitSignoffRequired(Boolean webCommitSignoffRequired) {
      this.webCommitSignoffRequired = webCommitSignoffRequired;
   }

   public List<String> getTopics() {
      return topics;
   }

   public void setTopics(List<String> topics) {
      this.topics = topics;
   }

   public String getVisibility() {
      return visibility;
   }

   public void setVisibility(String visibility) {
      this.visibility = visibility;
   }

   public Integer getForks() {
      return forks;
   }

   public void setForks(Integer forks) {
      this.forks = forks;
   }

   public Integer getOpenIssues() {
      return openIssues;
   }

   public void setOpenIssues(Integer openIssues) {
      this.openIssues = openIssues;
   }


   public Integer getWatchers() {
      return watchers;
   }

   public void setWatchers(Integer watchers) {
      this.watchers = watchers;
   }

   public String getDefaultBranch() {
      return defaultBranch;
   }

   public void setDefaultBranch(String defaultBranch) {
      this.defaultBranch = defaultBranch;
   }

//   @ColumnInfo(name = "permissions")
   public Permissions getPermissions() {
      return permissions;
   }

   public void setPermissions(Permissions permissions) {
      this.permissions = permissions;
   }

   public int getOwnerId()
   {
      return ownerId;
   }

   public void setOwnerId(int ownerId)
   {
      this.ownerId = ownerId;
   }
}
