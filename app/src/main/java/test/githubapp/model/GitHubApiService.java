package test.githubapp.model;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GitHubApiService
{
   private static final String BASE_URL = "https://api.github.com";

   private GitHubApi api;

   public GitHubApiService(String token)
   {
      OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
               @Override
               public okhttp3.Response intercept(Chain chain) throws IOException
               {
                  Request originalRequest = chain.request();

//                  Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                                                                                Credentials.basic(username, password));

//                  Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                                                                                "token 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");

                  Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                                                                                "token " + token);


                  Request newRequest = builder.build();
                  return chain.proceed(newRequest);
               }
            }).build();

      api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitHubApi.class);
   }

   public Single<List<Repository>> getRepositories()
   {
      return api.getRepositories();
   }

   public Single<List<User>> getContributors(String owner, String repository)
   {
      return api.getContributors(owner, repository);
   }

   public Single<List<User>> getFollowers(String owner)
   {
      return api.getFollowers(owner);
   }

   public Single<List<User>> getFollowing(String owner)
   {
      return api.getFollowing(owner);
   }
}
