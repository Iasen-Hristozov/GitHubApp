package test.githubapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi
{
//   @GET("user/repos?per_page=100")
   @GET("user/repos")
   Single<List<Repository>> getRepositories();


   @GET("repos/{owner}/{repository}/contributors")
   Single<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repository") String repository);

   @GET("users/{owner}/followers")
   Single<List<Contributor>> getFollowers(@Path("owner") String owner);

   @GET("users/{owner}/following")
   Single<List<Contributor>> getFollowing(@Path("owner") String owner);
}
