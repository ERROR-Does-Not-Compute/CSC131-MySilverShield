package com.example.myapplication.iceinfo.allergies;

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


public class AllergyDialog extends AppCompatDialogFragment {

    private EditText editTextAllergy;

    private AllergyDialogListener listener;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_allergy_dialog, null);

        //Build Allergy Dialog
        builder.setView(view)
                .setTitle("My Allergies")
                .setNegativeButton("CANCEL", (dialogInterface, i) -> {

                })
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    String newAllergy = editTextAllergy.getText().toString();

                    //Check if entry is filled and if so, return it, but if not, return "Fail! Must Fill Entry!" message
                    if(newAllergy.length() > 0){
                        listener.applyAllergy(newAllergy);
                        Toast.makeText(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(getActivity(),"Fail! Must Fill Entry!",Toast.LENGTH_SHORT).show();
                    }
                });

        //Link editText object to respective layout
        editTextAllergy = view.findViewById(R.id.edit_allergy);

        //Return to ICEinfo Activity
        return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        //Check to make sure AllergyDialogListener is implemented
        try {
            listener = (AllergyDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement AllergyDialogListener");
        }
    }

    //Create the interface
    public interface AllergyDialogListener {
        void applyAllergy(String newAllergy);
    }
}