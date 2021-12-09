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

public class Step2B extends AppCompatActivity {

    private final StringBuilder step2b = new StringBuilder();
    private BufferedReader reader = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2_b);

        try {
            reader = new BufferedReader(
                    //Open step2.txt file
                    new InputStreamReader(getAssets().open("step2.txt")));

            //Read step2.txt file and loop until end of file
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                step2b.append(mLine);
                step2b.append('\n');
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
            output.setText(step2b);
        }

        //Link yes_button to Step3C Activity
        Button yes_button = findViewById(R.id.button1);
        yes_button.setOnClickListener(v -> {
            intent = new Intent(this, Step3C.class);
            startActivity(intent);
        });

        //Link no_button to Step3D Activity
        Button no_button = findViewById(R.id.button2);
        no_button.setOnClickListener(v -> {
            intent = new Intent(this, Step3D.class
            );
            startActivity(intent);
        });

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

    }
}