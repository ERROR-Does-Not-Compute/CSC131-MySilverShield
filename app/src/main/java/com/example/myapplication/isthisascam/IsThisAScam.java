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

public class IsThisAScam extends AppCompatActivity {

    private final StringBuilder step1_header = new StringBuilder();
    private final StringBuilder step1 = new StringBuilder();
    private BufferedReader reader = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_this_a_scam);

        String mLine;
        TextView output;
        try {
            reader = new BufferedReader(
                    //Open intro.txt file
                    new InputStreamReader(getAssets().open("intro.txt")));

            //Read intro.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step1_header.append(mLine);
                step1_header.append('\n');
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
            output = findViewById(R.id.textView2);
            output.setText(step1_header);
        }

        try {
            reader = new BufferedReader(
                    //Open step1.txt file
                    new InputStreamReader(getAssets().open("step1.txt")));

            //Read step1.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step1.append(mLine);
                step1.append('\n');
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
            output = findViewById(R.id.textView3);
            output.setMovementMethod(new ScrollingMovementMethod());
            output.setText(step1);
        }

        //Link step2_button to Step2 Activity
        Button step2_button = findViewById(R.id.button1);
        step2_button.setOnClickListener(v -> {
            intent = new Intent(this, Step2.class);
            startActivity(intent);
        });

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());
    }
}