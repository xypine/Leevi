package jonnelafin.leevi.ui.home;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jonnelafin.leevi.MainActivity;
import jonnelafin.leevi.R;
import jonnelafin.leevi.lemmyStructs.LemmyPost;

public class HomeViewModel extends ViewModel {

    private static MutableLiveData<String> mText;


    public HomeViewModel() {
  /*      mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/
    }

    /*
    public LiveData<String> getText() {
        return mText;
    }*/




}
