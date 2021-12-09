package com.example.myapplication.iceinfo.medicalconditions;

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


public class MedicalConditionDialog extends AppCompatDialogFragment {

    private EditText editTextMedicalCondition;

    private MedicalConditionDialogListener listener;

    private String newMedicalCondition;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_medical_condition_dialog, null);

        //Build Medical Condition Dialog
        builder.setView(view)
                .setTitle("My Medical Condition(s)")
                .setNegativeButton("CANCEL", (dialogInterface, i) -> {

                })
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    newMedicalCondition = editTextMedicalCondition.getText().toString();

                    //Check if entry is filled and if so, return it, but if not, return "Fail! Must Fill Entry!" message
                    if(newMedicalCondition.length() > 0){
                        listener.applyMedicalCondition(newMedicalCondition);
                        Toast.makeText(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(getActivity(),"Fail! Must Fill Entry!",Toast.LENGTH_SHORT).show();
                    }
                });

        //Link editText object to respective layout
        editTextMedicalCondition = view.findViewById(R.id.edit_medicalCondition);

        //Return to ICEinfo Activity
        return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        //Check to make sure MedicalConditionDialogListener is implemented
        try {
            listener = (MedicalConditionDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement MedicalConditionDialogListener");
        }
    }

    //Create the interface
    public interface MedicalConditionDialogListener {
        void applyMedicalCondition(String newMedicalCondition);
    }
}