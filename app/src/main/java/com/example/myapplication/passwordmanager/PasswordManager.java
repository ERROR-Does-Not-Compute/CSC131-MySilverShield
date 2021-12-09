package com.example.myapplication.passwordmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.List;

public class PasswordManager extends AppCompatActivity implements PasswordDialog.PasswordDialogListener {

    private PasswordsDatabaseHelper db;
    private List<PasswordDesign> list;
    private PasswordsCustomAdapter passwords_customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);

        //Link password_button to openPasswordManagerDialog Activity
        Button password_button = findViewById(R.id.Button1);
        ListView listView= findViewById(R.id.ListView);

        db=new PasswordsDatabaseHelper(this);
        list=db.getAllPasswords();
        passwords_customAdapter=new PasswordsCustomAdapter(this,list);
        listView.setAdapter(passwords_customAdapter);

        //Collect Password by launching openPasswordManagerDialog, and only let the user display one set of info at a time.
        password_button.setOnClickListener(v -> openDialog());

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());
    }

    public void openDialog() {
        PasswordDialog passwordDialog = new PasswordDialog();
        //Display PasswordDialog and collect info
        passwordDialog.show(getSupportFragmentManager(), "password dialog");
    }

    //Use applyPassword to take the info collected from PasswordDialog, add it to a database, then from the database print a list and use the passwords_customAdapter to display it
    @Override
    public void applyPassword(String newAppName, String newUsername, String newPassword) {

        db.addPassword(new PasswordDesign(0,newAppName,newUsername,newPassword));
        list=db.getAllPasswords();
        passwords_customAdapter.refresh(list);
    }

}