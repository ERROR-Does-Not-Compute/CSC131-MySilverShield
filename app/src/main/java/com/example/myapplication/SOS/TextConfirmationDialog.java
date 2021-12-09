package com.example.myapplication.SOS;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class TextConfirmationDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Display Text Confirmation Message
        builder.setTitle("My Silver Shield: SOS")
                .setMessage("YOUR SOS MESSAGE IS BEING SENT TO YOUR EMERGENCY CONTACTS!")
                .setPositiveButton("Dismiss", (dialogInterface, i) -> {
                });
        return builder.create();
    }
}
