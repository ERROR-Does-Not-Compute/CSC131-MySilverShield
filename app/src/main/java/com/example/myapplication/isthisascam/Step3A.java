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

public class Step3A extends AppCompatActivity {

    private final StringBuilder step3a_header = new StringBuilder();
    private final StringBuilder step3a = new StringBuilder();
    private BufferedReader reader = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        String mLine;
        TextView output;
        try {
            reader = new BufferedReader(
                    //Open step3a_header.txt file
                    new InputStreamReader(getAssets().open("step3a_header.txt")));

            //Read step3a_header.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step3a_header.append(mLine);
                step3a_header.append('\n');
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
            output.setText(step3a_header);
        }

        try {
            reader = new BufferedReader(
                    //Open step3a.txt file
                    new InputStreamReader(getAssets().open("step3a.txt")));

            //Read step3a.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step3a.append(mLine);
                step3a.append('\n');
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
            output.setText(step3a);
        }

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