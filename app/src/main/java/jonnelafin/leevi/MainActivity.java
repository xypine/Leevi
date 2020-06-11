package jonnelafin.leevi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import jonnelafin.leevi.ui.home.HomeFragment;
import jonnelafin.leevi.ui.home.HomeViewModel;

public class MainActivity extends AppCompatActivity implements WebSocketListener {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        loadPosts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    String host = "dev.lemmy.ml";
    String url = "wss://" + host + "/api/v1/ws";
    public void loadPosts(){
        Map<String, Object> arg = new HashMap();
        arg.put("op", "GetPosts");
            Map<String,String> inpData = new HashMap<>();
            inpData.put("type_", "All");
            inpData.put("sort", "TopAll");
        arg.put("data", inpData);
        WebSocket.connectWebSocket(url, arg, this);
    }

    @Override
    public void process(String data) {
        try {

            JSONObject obj = new JSONObject(data);

            //TextView tv1 = (TextView)findViewById(R.id.text_home);
            //tv1.setText(obj.toString());
            String op = (String) obj.get("op");
            if(op.equals("GetPosts")) {
                Log.i("OP Check", "Got the post data we wanted.");
                HomeFragment.setPosts(obj, this);
            }
            else{
                Log.i("OP Check", "Not looking for data with op \"" + op + "\", ignoring data: " + data);
            }

            Log.i("Raw JSON", obj.toString());

        } catch (Throwable t) {

            Log.e("My App", "Could not parse malformed JSON: \"" + data + "\"");
            Log.e("Error: ", t + "");
        }
    }
}