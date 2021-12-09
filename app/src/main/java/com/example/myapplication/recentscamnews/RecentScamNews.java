package com.example.myapplication.recentscamnews;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.isthisascam.Web1;
import com.example.myapplication.isthisascam.Web2;

public class RecentScamNews extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_scam_news);

        //Link web1_button to Web1 Activity
        Button web1_button = findViewById(R.id.button1);
        web1_button.setOnClickListener(v -> {
            intent = new Intent(this, Web1.class);
            startActivity(intent);
        });

        //Link web2_button to Web2 Activity
        Button web2_button = findViewById(R.id.button2);
        web2_button.setOnClickListener(v -> {
            intent = new Intent(this, Web2.class);
            startActivity(intent);
        });

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());
    }
}