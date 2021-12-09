package com.example.myapplication.iceinfo.medications;

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


public class MedicationDialog extends AppCompatDialogFragment {

    private EditText editTextMedication;

    private MedicationDialogListener listener;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_medication_dialog, null);

        //Build Medication Dialog
        builder.setView(view)
                .setTitle("My Medication(s)")
                .setNegativeButton("CANCEL", (dialogInterface, i) -> {

                })
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    String newMedication = editTextMedication.getText().toString();

                    //Check if entry is filled and if so, return it, but if not, return "Fail! Must Fill Entry!" message
                    if(newMedication.length() > 0){
                        listener.applyMedication(newMedication);
                        Toast.makeText(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(getActivity(),"Fail! Must Fill Entry!",Toast.LENGTH_SHORT).show();
                    }
                });

        //Link editText object to respective layout
        editTextMedication = view.findViewById(R.id.edit_medication);

        //Return to ICEinfo Activity
        return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        //Check to make sure MedicationDialogListener is implemented
        try {
            listener = (MedicationDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement MedicationDialogListener");
        }
    }

    //Create the interface
    public interface MedicationDialogListener {
        void applyMedication(String newMedication);
    }
}