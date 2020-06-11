package jonnelafin.leevi.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jonnelafin.leevi.lemmyStructs.LemmyPost;

public class HomeViewModel extends ViewModel {

    private static MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public static void setPosts(JSONObject raw){
        try {
            JSONObject data = raw.getJSONObject("data");
            JSONArray posts = data.getJSONArray("posts");
            for(int i = 0; i < posts.length(); i++){
                JSONObject p = posts.getJSONObject(i);
                LemmyPost post = LemmyPost.fromJson(p.toString());
                addPostCard(post);
            }
        } catch (JSONException e) {
            Log.e("Invalid JSON Data", e+"");
        }
    }

    private static void addPostCard(LemmyPost post){

    }
}
