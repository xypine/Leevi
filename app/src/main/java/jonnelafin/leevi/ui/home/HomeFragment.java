package jonnelafin.leevi.ui.home;

import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import jonnelafin.leevi.MainActivity;
import jonnelafin.leevi.R;
import jonnelafin.leevi.lemmyStructs.LemmyPost;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static LinearLayout layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        layout = container.findViewById(R.id.feed_layout);
        //final TextView textView = root.findViewById(R.id.text_home);
/*        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
        //        textView.setText(s);
            }
        });*/

        return root;
    }
    public static void setPosts(JSONObject raw, MainActivity context){
        try {
            JSONObject data = raw.getJSONObject("data");
            JSONArray posts = data.getJSONArray("posts");
            for(int i = 0; i < posts.length(); i++){
                JSONObject p = posts.getJSONObject(i);
                LemmyPost post = LemmyPost.fromJson(p.toString());
                Log.i("Adding post to UI", post.name);
                addPostCard(post, context);
            }
        } catch (JSONException e) {
            Log.e("Invalid JSON Data", e+"");
        }
    }

    private static void addPostCard(LemmyPost post, final MainActivity context){
        final CardView card = new CardView(context);


        final LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        card.setLayoutParams(layoutparams);
        card.setRadius(25);
        card.setPadding(25, 25, 25, 50);
        card.setCardBackgroundColor(Color.WHITE);
        card.setMaxCardElevation(30);
        card.setMaxCardElevation(6);

        TextView Title = new TextView(context);
        Title.setLayoutParams(layoutparams);
        Title.setText(post.name);
        Title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        Title.setTextColor(Color.BLACK);
        Title.setPadding(25,25,25,25);
        Title.setGravity(Gravity.BOTTOM);

        TextView author = new TextView(context);
        author.setLayoutParams(layoutparams);
        author.setText(post.creator_name);
        author.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.5F);
        author.setTextColor(Color.BLACK);
        author.setPadding(25,25,25,50);
        author.setGravity(Gravity.TOP);

        card.addView(Title);
        card.addView(author);
        layout = context.findViewById(R.id.feed_layout);
        card.setMinimumWidth(layout.getWidth());
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // TODO your Code
                layout.setBaselineAligned(true);

                layout.addView(card);
            }
        });
        //layout.addView(card);
    }
}