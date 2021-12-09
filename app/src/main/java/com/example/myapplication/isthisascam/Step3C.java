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

public class Step3C extends AppCompatActivity {

    private final StringBuilder step3c_header = new StringBuilder();
    private final StringBuilder step3c = new StringBuilder();
    private final StringBuilder step3c_footer = new StringBuilder();
    private BufferedReader reader = null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3_c);

        TextView output;
        String mLine;
        try {
            reader = new BufferedReader(
                    //Open step3c_header.txt file
                    new InputStreamReader(getAssets().open("step3c_header.txt")));

            //Read step3c_header.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step3c_header.append(mLine);
                step3c_header.append('\n');
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
            output.setText(step3c_header);
        }

        try {
            reader = new BufferedReader(
                    //Open step3c.txt
                    new InputStreamReader(getAssets().open("step3c.txt")));

            //Read step3c.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step3c.append(mLine);
                step3c.append('\n');
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
            output.setText(step3c);
        }

        try {
            reader = new BufferedReader(
                    //Open step3c_footer.txt file
                    new InputStreamReader(getAssets().open("step3c_footer.txt")));

            //Read step3c_footer.txt file and loop until end of file
            while ((mLine = reader.readLine()) != null) {
                step3c_footer.append(mLine);
                step3c_footer.append('\n');
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
            output = findViewById(R.id.textView4);
            output.setMovementMethod(new ScrollingMovementMethod());
            output.setText(step3c_footer);
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

        //Link web3_button to Web3 Activity
        Button web3_button = findViewById(R.id.button3);
        web3_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Web3.class);
            startActivity(intent);
        });

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

    }
}