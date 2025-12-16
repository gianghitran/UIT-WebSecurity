package com.example.taskc4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

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
//        Intent read_contact=new Intent();
//        read_contact.setAction(android.content.Intent.ACTION_VIEW);
//        read_contact.setData(ContactsContract.Contacts.CONTENT_URI);
//        startActivity(read_contact);
    }
}