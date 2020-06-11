package jonnelafin.leevi;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class WebSocket {
    private WebSocketClient mWebSocketClient;
//    String host = "dev.lemmy.ml";
//    String url = "wss://" + host + "/api/v1/ws";
    /*
        Map<String,Object> arg = new HashMap();
        arg.put("op", "GetPosts");
        Map<String,String> inpData = new HashMap<>();
                inpData.put("type_", "All");
                inpData.put("sort", "TopAll");
        arg.put("data", inpData);
     */




    public static void connectWebSocket(final String url, final Map<String,Object> arg, final WebSocketListener listener) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        WebSocketClient mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                Log.i("Websocket", "Connecting to : " + url);
                JSONObject data = new JSONObject(arg);
                Log.i("Websocket Data", "set to : " + data);
                send(data.toString());
                Log.i("Websocket", "Data sent.");
            }

            @Override
            public void onMessage(String s) {
                Log.i("Websocket", "Data received.");
                Log.i("Websocket Data", s);
                final String message = s;
                listener.process(s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    public void sendMessage(View view) {
 //       EditText editText = (EditText)findViewById(R.id.message);
 //       mWebSocketClient.send(editText.getText().toString());
 //       editText.setText("");
    }
}