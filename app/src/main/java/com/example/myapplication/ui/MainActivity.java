package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.flashlight.Flashlight;
import com.example.myapplication.iceinfo.ICEinfo;
import com.example.myapplication.passwordmanager.PasswordManager;
import com.example.myapplication.R;
import com.example.myapplication.recentscamnews.RecentScamNews;
import com.example.myapplication.SOS.SOS;
import com.example.myapplication.isthisascam.IsThisAScam;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link button to IsThisAScam Activity
        Button button = findViewById(R.id.button1);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, IsThisAScam.class);
            startActivity(intent);
        });

        //Link button2 to RecentScamNews Activity
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecentScamNews.class);
            startActivity(intent);
        });

        //Link button3 to SOS Activity
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(this, SOS.class);
            startActivity(intent);
        });

        //Link button4 to ICEinfo Activity
        Button button4= findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            Intent intent = new Intent(this, ICEinfo.class);
            startActivity(intent);
        });

        //Link button5 to Flashlight Activity
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            Intent intent = new Intent(this, Flashlight.class);
            startActivity(intent);
        });

        //Link button6 to PasswordManager Activity
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> {
            Intent intent = new Intent(this, PasswordManager.class);
            startActivity(intent);
        });

    }
}