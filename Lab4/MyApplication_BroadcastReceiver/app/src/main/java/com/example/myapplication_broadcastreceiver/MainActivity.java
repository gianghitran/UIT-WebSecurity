package com.example.myapplication_broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent("theBroadcast");
        intent.putExtra("phonenumber", "+84900909090");
        intent.putExtra("newpass", "Please give me a cup of coffee");
        sendBroadcast(intent);
    }
}
