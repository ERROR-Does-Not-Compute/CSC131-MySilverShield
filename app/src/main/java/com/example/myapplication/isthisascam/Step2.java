package com.example.myapplication.isthisascam;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Step2 extends AppCompatActivity {

    private final StringBuilder step2 = new StringBuilder();
    private BufferedReader reader = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        try {
            reader = new BufferedReader(
                    //Open step2.txt file
                    new InputStreamReader(getAssets().open("step2.txt")));

            //Read step2.txt file and loop until end of file
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                    step2.append(mLine);
                    step2.append('\n');
                }
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        //log exception
                    }
                }
                //Output file to screen
                TextView output = findViewById(R.id.textView2);
                output.setMovementMethod(new ScrollingMovementMethod());
                output.setText(step2);
            }

        //Link yes_button to Step3A Activity
        Button yes_button = findViewById(R.id.button1);
        yes_button.setOnClickListener(v -> {
            intent = new Intent(this, Step3A.class);
            startActivity(intent);
        });

        //Link no_button to Step2A Activity
        Button no_button = findViewById(R.id.button2);
        no_button.setOnClickListener(v -> {
            intent = new Intent(this, Step2A.class);
            startActivity(intent);
        });

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

    }
}