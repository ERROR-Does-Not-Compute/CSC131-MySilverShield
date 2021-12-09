package com.example.myapplication.passwordmanager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

public class PasswordDialog extends AppCompatDialogFragment {

    private EditText editTextAppName, editTextUsername, editTextPassword;
    private PasswordDialogListener listener;
    private String newAppName, newUsername, newPassword;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_password_dialog, null);

        //Build Password Dialog
        builder.setView(view)
                .setTitle("Enter Account Details")
                .setNegativeButton("cancel", (dialogInterface, i) -> {

                })
                .setPositiveButton("ok", (dialogInterface, i) -> {
                    newAppName = editTextAppName.getText().toString();
                    newUsername = editTextUsername.getText().toString();
                    newPassword = editTextPassword.getText().toString();

                    //Check if all entries are filled and if so, return it, but if not, return "Fail! Must Fill All Entries!" message
                    if(newAppName.length() > 0 && newUsername.length() > 0 && newPassword.length() > 0){
                        newAppName = "App / Site Name: " + newAppName;
                        newUsername = "Username: " + newUsername;
                        newPassword = "Password: " + newPassword;
                        listener.applyPassword(newAppName, newUsername, newPassword);
                        Toast.makeText(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(getActivity(),"Fail! Must Fill All Entries!",Toast.LENGTH_SHORT).show();
                    }
                });

        //Link editText objects to respective layouts
        editTextAppName = view.findViewById(R.id.edit_AppName);
        editTextUsername = view.findViewById(R.id.edit_Username);
        editTextPassword = view.findViewById(R.id.edit_Password);

            //Return to PasswordManager Activity
            return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        //Check to make sure PasswordDialogListener is implemented
        try {
            listener = (PasswordDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement PasswordDialogListener");
        }
    }

    //Create the interface
    public interface PasswordDialogListener {
        void applyPassword(String newAppName, String newUsername, String newPassword);
    }
}


    



