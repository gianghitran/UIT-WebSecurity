package com.example.myapplication;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Android Mobile", "=== onCreate() ===");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("Android Mobile ", "Hello World");
        StrictMode.setThreadPolicy(new Builder().permitAll().build());
        String url = "https://uit.edu.vn/robots.txt";
        StringBuilder url_holder = new StringBuilder();
        url_holder.append(url);
        try {
            URLConnection conn = new URL(url_holder.toString()).openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-formurlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response;
            String data_from_stream;
            for (response = new String(); true; response += data_from_stream) {
                String stream = buffer.readLine();
                data_from_stream = stream;
                if (stream == null) {
                    break;
                }
            }
            Log.i("UIT ================= ", response);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

    }
    //    Called when the activity is about to become visible. 
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Android Mobile", "=== onStart() ===");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Android Mobile", "=== onResume() ===");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Android Mobile", "=== onPause() ===");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Android Mobile", "=== onStop() ===");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Android Mobile", "=== onDestroy() ===");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Android Mobile", "=== onRestart() ===");
    }
//    @Override
//    protected void onFreeze() {
//        super.onFreeze();
//        Log.i("Android Mobile", "=== onFreeze() ===");
//    }
}