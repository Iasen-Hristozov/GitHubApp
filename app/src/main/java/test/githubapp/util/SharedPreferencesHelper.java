package test.githubapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesHelper
{
   private static final String PREF_TIME = "Pref time";
   private static final String PREF_TOKEN = "token";
   private static SharedPreferencesHelper instance;
   private final  SharedPreferences       sharedPreferences;

   private SharedPreferencesHelper(Context context)
   {
      sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
   }

   public static SharedPreferencesHelper getInstance(Context context)
   {
      if(instance == null)
         instance = new SharedPreferencesHelper(context);
      return instance;
   }

   public void saveUpdateTime(long time)
   {
      sharedPreferences.edit().putLong(PREF_TIME, time).apply();
   }

   public long getUpdateTime()
   {
      return sharedPreferences.getLong(PREF_TIME, 0);
   }

   public String getCachedDuration()
   {
      return sharedPreferences.getString("pref_cache_duration", "");
   }

   public void saveToken(String token)
   {
      sharedPreferences.edit().putString(PREF_TOKEN, token).apply();
   }

   public String getToken()
   {
      return sharedPreferences.getString(PREF_TOKEN, "");
   }

}
