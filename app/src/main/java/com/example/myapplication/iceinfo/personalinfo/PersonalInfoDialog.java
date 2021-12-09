package com.example.myapplication.iceinfo.personalinfo;

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


public class PersonalInfoDialog extends AppCompatDialogFragment {

    private EditText editTextFirstName, editTextLastName, editTextDateOfBirth, editTextAddress,
            editTextCity, editTextZipcode, editPhoneNumber, editTextInsuranceProvider,
            editTextPolicyNumber, editTextBloodType;

    private PersonalInfoDialogListener listener;

    private String newFirstName, newLastName, newDateOfBirth, newAddress, newCity, newZipcode,
            newPhoneNumber, newInsuranceProvider, newPolicyNumber, newBloodType;

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_personal_info_dialog, null);

        //Build Personal Info Dialog
        builder.setView(view)
                .setTitle("My Personal Information")
                .setNegativeButton("CANCEL", (dialogInterface, i) -> {

                })
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    newFirstName = editTextFirstName.getText().toString();
                    newLastName = editTextLastName.getText().toString();
                    newDateOfBirth = editTextDateOfBirth.getText().toString();
                    newAddress = editTextAddress.getText().toString();
                    newCity = editTextCity.getText().toString();
                    newZipcode = editTextZipcode.getText().toString();
                    newPhoneNumber = editPhoneNumber.getText().toString();
                    newInsuranceProvider = editTextInsuranceProvider.getText().toString();
                    newPolicyNumber = editTextPolicyNumber.getText().toString();
                    newBloodType = editTextBloodType.getText().toString();

                    //Check if all entries are filled and if so, return it, but if not, return "Fail! Must Fill All Entries!" message
                    if(newFirstName.length() > 0 && newLastName.length() > 0 &&
                            newDateOfBirth.length() > 0 && newAddress.length() > 0 &&
                            newCity.length() > 0 && newZipcode.length() > 0 &&
                            newPhoneNumber.length() > 0 && newInsuranceProvider.length() > 0 &&
                            newPolicyNumber.length() > 0 && newBloodType.length() > 0){
                        newFirstName = "First Name: " + newFirstName;
                        newLastName = "Last Name: " + newLastName;
                        newDateOfBirth = "Date Of Birth: " + newDateOfBirth;
                        newAddress = "Address: " + newAddress;
                        newCity = "City: " + newCity;
                        newZipcode = "Zipcode: " + newZipcode;
                        newPhoneNumber = "Phone Number: " + newPhoneNumber;
                        newInsuranceProvider = "Insurance Provider: " + newInsuranceProvider;
                        newPolicyNumber = "Policy Number: " + newPolicyNumber;
                        newBloodType = "Blood Type: " + newBloodType;
                        listener.applyPersonalInfo(newFirstName, newLastName, newDateOfBirth,
                                newAddress, newCity, newZipcode, newPhoneNumber,
                                newInsuranceProvider, newPolicyNumber, newBloodType);
                        Toast.makeText(getActivity(),"Success!",Toast.LENGTH_SHORT).show();
                    }else{

                        Toast.makeText(getActivity(),"Fail! Must Fill All Entries!",Toast.LENGTH_SHORT).show();
                    }
                });

        //Link editText objects to respective layouts
        editTextFirstName = view.findViewById(R.id.edit_firstName);
        editTextLastName = view.findViewById(R.id.edit_lastName);
        editTextDateOfBirth = view.findViewById(R.id.edit_dateOfBirth);
        editTextAddress = view.findViewById(R.id.edit_Address);
        editTextCity = view.findViewById(R.id.edit_City);
        editTextZipcode = view.findViewById(R.id.edit_Zipcode);
        editPhoneNumber = view.findViewById(R.id.edit_phoneNumber);
        editTextInsuranceProvider = view.findViewById(R.id.edit_insuranceProvider);
        editTextPolicyNumber = view.findViewById(R.id.edit_policyNumber);
        editTextBloodType = view.findViewById(R.id.edit_bloodType);

        //Return to ICEinfo Activity
        return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        //Check to make sure PersonalDialogListener is implemented
        try {
            listener = (PersonalInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement PersonalDialogListener");
        }
    }

    //Create the interface
    public interface PersonalInfoDialogListener {
        void applyPersonalInfo(String newFirstName, String newLastName, String newDateOfBirth,
                               String newAddress, String newCity, String newZipcode,
                               String newPhoneNumber, String newInsuranceProvider,
                               String newPolicyNumber, String newBloodType);
    }
}