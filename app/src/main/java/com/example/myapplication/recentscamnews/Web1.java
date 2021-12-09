package com.example.myapplication.recentscamnews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Web1 extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);

        WebView myWebView = findViewById(R.id.webView);

        //Enable JavaScript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Link URL to Web1 Activity webView
        myWebView.loadUrl("https://www.usa.gov/common-scams-frauds");

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

    }
}