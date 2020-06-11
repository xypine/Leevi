package jonnelafin.leevi.ui.home;

import android.graphics.Color;
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
    private static boolean update = false;
    private static LinkedList<CardView> toAdd = new LinkedList<>();
    private static boolean clear = false;

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
        Thread uiThread = new Thread(){
            @Override
            public void run(){
                layout = new LinearLayout();
                while (true){
                    if(update){
                        update = false;
                        for (CardView i : toAdd){
                            layout.addView(i);
                        }
                        toAdd.clear();
                    }
                    if(clear){
                        clear = false;
                        layout.removeAllViews();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        uiThread.start();
        return root;
    }
    public static void setPosts(JSONObject raw, MainActivity context){
        clear = true;
        try {
            JSONObject data = raw.getJSONObject("data");
            JSONArray posts = data.getJSONArray("posts");
            for(int i = 0; i < posts.length(); i++){
                JSONObject p = posts.getJSONObject(i);
                LemmyPost post = LemmyPost.fromJson(p.toString());
                addPostCard(post, context);
            }
        } catch (JSONException e) {
            Log.e("Invalid JSON Data", e+"");
        }
    }

    private static void addPostCard(LemmyPost post, MainActivity context){
        CardView card = new CardView(context);


        LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        card.setLayoutParams(layoutparams);
        card.setRadius(15);
        card.setPadding(25, 25, 25, 25);
        card.setCardBackgroundColor(Color.MAGENTA);
        card.setMaxCardElevation(30);
        card.setMaxCardElevation(6);

        TextView textview = new TextView(context);
        textview.setLayoutParams(layoutparams);
        textview.setText(String.valueOf(post.ID));
        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
        textview.setTextColor(Color.WHITE);
        textview.setPadding(25,25,25,25);
        textview.setGravity(Gravity.CENTER);

        card.addView(textview);
        layout = context.findViewById(R.id.feed_layout);
        toAdd.add(card);
        update = true;
        //layout.addView(card);
    }
}