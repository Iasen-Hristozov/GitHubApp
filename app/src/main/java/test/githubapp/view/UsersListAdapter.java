package test.githubapp.view;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import test.githubapp.R;
import test.githubapp.model.User;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserViewHolder>
{
   private final Context context;

   private final List<User> usersList;

   private String filterString = "";

   List<User> visibleUserItems = new ArrayList<>();

   public UsersListAdapter(Context context, List<User> usersList)
   {
      this.context = context;

      this.usersList = usersList;

      this.visibleUserItems.addAll(usersList);
   }

   @NonNull
   @Override
   public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
   {
      View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_user, parent, false);

      return new UserViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
   {
//      holder.getUserTextView().setText(visibleUserItems.get(position).getLogin());

      holder.bind(position);
   }

   @Override
   public int getItemCount()
   {
      return visibleUserItems.size();
   }

   class UserViewHolder extends RecyclerView.ViewHolder
   {
      private final TextView userTextView;

      public UserViewHolder(@NonNull View itemView)
      {
         super(itemView);
         userTextView = (TextView) itemView.findViewById(R.id.userTextView);
      }

      public TextView getUserTextView()
      {
         return userTextView;
      }

      void bind(int position)
      {
         String text = visibleUserItems.get(position).getLogin();
         Spannable contentSpannable = new SpannableString(text);
         int filteredStart = text.toLowerCase(Locale.getDefault()).indexOf(filterString.toLowerCase(Locale.getDefault()));
         int filterEnd;
         if(filteredStart < 0)
         {
            filteredStart = 0;
            filterEnd = 0;
         }
         else
            filterEnd = filteredStart + filterString.length();
         contentSpannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.purple_200)),
                                  filteredStart,
                                  filterEnd,
                                  Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
         userTextView.setText(contentSpannable);
      }

   }

   public void filter(String charText)
   {
      charText = charText.toLowerCase(Locale.getDefault());
      filterString = charText;

      ArrayList<User> usersListTmp = new ArrayList<>();
      visibleUserItems.clear();
      if(charText.length() == 0)
      {
         visibleUserItems.addAll(usersList);
      }
      else
      {
         for(User user : usersList)
         {
            if(user.getLogin().toLowerCase(Locale.getDefault()).contains(charText))
            {
               usersListTmp.add(user);
            }
         }
         visibleUserItems.addAll(usersListTmp);
      }
      notifyDataSetChanged();
   }
}
