package com.example.myapplication.isthisascam;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Web2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2);

        WebView myWebView = findViewById(R.id.webView);

        //Link URL to Web2 Activity webView
        myWebView.loadUrl("https://www.consumer.ftc.gov/features/scam-alerts");

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

    }
}