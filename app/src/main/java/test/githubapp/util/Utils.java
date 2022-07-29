package test.githubapp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import test.githubapp.R;

public class Utils
{
   public static void loadImage(ImageView imageView,
                                String url,
                                CircularProgressDrawable progressDrawable)
   {
      RequestOptions options = new RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher);
      Glide.with(imageView.getContext())
           .setDefaultRequestOptions(options)
           .load(url)
           .into(imageView);
   }

   public static CircularProgressDrawable getProgressDrawable(Context context)
   {
      CircularProgressDrawable cpd = new CircularProgressDrawable(context);
      cpd.setStrokeWidth(10f);
      cpd.setCenterRadius(50f);
      cpd.start();
      return cpd;
   }
}
