package com.example.myapplication.iceinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.iceinfo.allergies.AllergiesCustomAdapter;
import com.example.myapplication.iceinfo.allergies.AllergiesDatabaseHelper;
import com.example.myapplication.iceinfo.allergies.AllergiesDesign;
import com.example.myapplication.iceinfo.allergies.AllergyDialog;
import com.example.myapplication.iceinfo.medicalconditions.MedicalConditionDialog;
import com.example.myapplication.iceinfo.medicalconditions.MedicalConditionsCustomAdapter;
import com.example.myapplication.iceinfo.medicalconditions.MedicalConditionsDatabaseHelper;
import com.example.myapplication.iceinfo.medicalconditions.MedicalConditionsDesign;
import com.example.myapplication.iceinfo.medications.MedicationDialog;
import com.example.myapplication.iceinfo.medications.MedicationsCustomAdapter;
import com.example.myapplication.iceinfo.medications.MedicationsDatabaseHelper;
import com.example.myapplication.iceinfo.medications.MedicationsDesign;
import com.example.myapplication.iceinfo.personalinfo.PersonalInfoCustomAdapter;
import com.example.myapplication.iceinfo.personalinfo.PersonalInfoDatabaseHelper;
import com.example.myapplication.iceinfo.personalinfo.PersonalInfoDesign;
import com.example.myapplication.iceinfo.personalinfo.PersonalInfoDialog;

import java.util.List;

public class ICEinfo extends AppCompatActivity implements PersonalInfoDialog.PersonalInfoDialogListener, MedicalConditionDialog.MedicalConditionDialogListener, MedicationDialog.MedicationDialogListener, AllergyDialog.AllergyDialogListener {

    private PersonalInfoDatabaseHelper pi_db;
    private List<PersonalInfoDesign> personal_info_list;
    private PersonalInfoCustomAdapter personal_info_customAdapter;

    private MedicalConditionsDatabaseHelper mc_db;
    private List<MedicalConditionsDesign> medical_conditions_list;
    private MedicalConditionsCustomAdapter medical_conditions_customAdapter;

    private MedicationsDatabaseHelper m_db;
    private List<MedicationsDesign> medications_list;
    private MedicationsCustomAdapter medications_customAdapter;

    private AllergiesDatabaseHelper a_db;
    private List<AllergiesDesign> allergies_list;
    private AllergiesCustomAdapter allergies_customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_c_einfo);


        /*<----------------------Personal Info Section---------------------->*/

        //Link personal_info_button to openPersonalInfoDialog Activity
        Button personal_info_button = findViewById(R.id.Button1);
        ListView personal_info_listView = findViewById(R.id.ListView1);

        pi_db=new PersonalInfoDatabaseHelper(this);
        personal_info_list=pi_db.getAllPersonalInfo();
        personal_info_customAdapter=new PersonalInfoCustomAdapter(this,personal_info_list);
        personal_info_listView.setAdapter(personal_info_customAdapter);

        //Collect Personal Info by launching openPersonalInfoDialog, and only let the user display one set of info at a time.
        personal_info_button.setOnClickListener(v -> {

            if(pi_db.count()!=1) {
                openPersonalInfoDialog();
            }else{
                Toast.makeText(ICEinfo.this, "Delete existing info before trying to add new info!", Toast.LENGTH_SHORT).show();
            }

        });

        /*<----------------------Medical Conditions Section---------------------->*/

        //Link medical_conditions_button to openMedicalConditionDialog Activity
        Button medical_conditions_button = findViewById(R.id.Button2);
        ListView medical_conditions_listView = findViewById(R.id.ListView2);

        mc_db=new MedicalConditionsDatabaseHelper(this);
        medical_conditions_list=mc_db.getAllMedicalConditions();
        medical_conditions_customAdapter=new MedicalConditionsCustomAdapter(this,medical_conditions_list);
        medical_conditions_listView.setAdapter(medical_conditions_customAdapter);

        //Collect Medical Conditions by launching openMedicalConditionDialog
        medical_conditions_button.setOnClickListener(v -> openMedicalConditionDialog());

        /*<----------------------Medications Section---------------------->*/

        //Link medications_button to openMedicationDialog Activity
        Button medications_button = findViewById(R.id.Button3);
        ListView medications_listView = findViewById(R.id.ListView3);

        m_db=new MedicationsDatabaseHelper(this);
        medications_list=m_db.getAllMedications();
        medications_customAdapter=new MedicationsCustomAdapter(this,medications_list);
        medications_listView.setAdapter(medications_customAdapter);

        //Collect Medications by launching openMedicationDialog
        medications_button.setOnClickListener(v -> openMedicationDialog());

        /*<----------------------Allergies Section---------------------->*/

        //Link allergies_button to openAllergyDialog Activity
        Button allergies_button = findViewById(R.id.Button4);
        ListView allergies_listView = findViewById(R.id.ListView4);

        a_db=new AllergiesDatabaseHelper(this);
        allergies_list=a_db.getAllAllergies();
        allergies_customAdapter=new AllergiesCustomAdapter(this,allergies_list);
        allergies_listView.setAdapter(allergies_customAdapter);

        //Collect Allergies by launching openAllergyDialog
        allergies_button.setOnClickListener(v -> openAllergyDialog());

        /*<----------------------Back Button---------------------->*/

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());
    }

    /*<----------------------Dialog Section---------------------->*/

    public void openPersonalInfoDialog() {
        PersonalInfoDialog personalInfoDialog = new PersonalInfoDialog();
        //Display PersonalInfoDialog and collect info
        personalInfoDialog.show(getSupportFragmentManager(), "personal info dialog");
    }

    public void openMedicalConditionDialog() {
        MedicalConditionDialog medicalConditionDialog = new MedicalConditionDialog();
        //Display MedicalConditionDialog and collect info
        medicalConditionDialog.show(getSupportFragmentManager(), "medical condition dialog");
    }

    public void openMedicationDialog() {
        MedicationDialog medicationDialog = new MedicationDialog();
        //Display MedicationDialog and collect info
        medicationDialog.show(getSupportFragmentManager(), "medication dialog");
    }

    public void openAllergyDialog() {
        AllergyDialog allergyDialog = new AllergyDialog();
        // Display AllergryDialog and collect info
        allergyDialog.show(getSupportFragmentManager(), "allergy dialog");
    }


    /*<----------------------"Apply" Section---------------------->*/
    @SuppressLint("SetTextI18n")

    //Use applyPersonalInfo to take the info collected from PersonalInfoDialog, add it to a database, then from the database print a list and use the personal_info_customAdapter to display it
    @Override
    public void applyPersonalInfo(String newFirstName, String newLastName, String newDateOfBirth,
                                  String newAddress, String newCity, String newZipcode, String newPhoneNumber,
                                  String newInsuranceProvider, String newPolicyNumber, String newBloodType) {
        pi_db.addPersonalInfo(new PersonalInfoDesign(0,newFirstName,newLastName,newDateOfBirth,
                newAddress,newCity,newZipcode,newPhoneNumber,newInsuranceProvider,newPolicyNumber,
                newBloodType));
        personal_info_list=pi_db.getAllPersonalInfo();
        personal_info_customAdapter.refresh(personal_info_list);
    }

    //Use applyMedicalCondition to take the info collected from MedicalConditionDialog, add it to a database, then from the database print a list and use the medical_conditions_customAdapter to display it
    @Override
    public void applyMedicalCondition(String newMedicalCondition) {
        mc_db.addMedicalCondition(new MedicalConditionsDesign(0,newMedicalCondition));
        medical_conditions_list=mc_db.getAllMedicalConditions();
        medical_conditions_customAdapter.refresh(medical_conditions_list);
    }

    //Use applyMedication to take the info collected from MedicationDialog, add it to a database, then from the database print a list and use the medications_customAdapter to display it
    @Override
    public void applyMedication(String newMedication) {
        m_db.addMedication(new MedicationsDesign(0,newMedication));
        medications_list=m_db.getAllMedications();
        medications_customAdapter.refresh(medications_list);
    }

    //Use applyAllergy to take the info collected from AllergyDialog, add it to a database, then from the database print a list and use the allergies_customAdapter to display it
    @Override
    public void applyAllergy(String newAllergy) {
        a_db.addAllergy(new AllergiesDesign(0,newAllergy));
        allergies_list=a_db.getAllAllergies();
        allergies_customAdapter.refresh(allergies_list);
    }
}


