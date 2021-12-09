package com.example.myapplication.SOS;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EmptyContactDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Display Error Message
        builder.setTitle("ERROR: ")
                .setMessage("YOUR EMERGENCY CONTACTS LIST MUST NOT BE EMPTY! \nCLICK THE 'ADD EMERGENCY CONTACT' BUTTON ON THE TOP TO ADD CONTACTS ")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                });
        return builder.create();
    }
}
