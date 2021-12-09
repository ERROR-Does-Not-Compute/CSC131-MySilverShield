package com.example.myapplication.iceinfo.medicalconditions;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class MedicalConditionsCustomAdapter extends ArrayAdapter<MedicalConditionsDesign> {

    private final Context context;
    private final List<MedicalConditionsDesign> medical_conditions_list;
    private MedicalConditionsDatabaseHelper mc_db;


    public MedicalConditionsCustomAdapter(@NonNull Context context, List<MedicalConditionsDesign> medical_conditions_list) {
        super(context, 0, medical_conditions_list);
        this.context=context;
        this.medical_conditions_list=medical_conditions_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        mc_db=new MedicalConditionsDatabaseHelper(context);
        MedicalConditionsDesign c = getItem(position);

        //Link Adapter to item_medical_condition layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_medical_condition, parent, false);
        }

        //Instantiate layout features
        LinearLayout linearLayout= convertView.findViewById(R.id.linear);
        TextView tvCondition = convertView.findViewById(R.id.tvMedicalCondition);

        //Add formatted data to database to medical conditions list
        tvCondition.setText(c.getMedicalCondition());


        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete medical condition
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Medical Condition")
                    .setMessage("Are you sure want to remove this medical condition")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        mc_db.deleteMedicalCondition(c);
                        medical_conditions_list.remove(c);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Medical Condition has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });

        //Display medical condition(s) to phone screen
        return convertView;
    }

    //Update ListView
    public void refresh(List<MedicalConditionsDesign> list){
        medical_conditions_list.clear();
        medical_conditions_list.addAll(list);
        notifyDataSetChanged();
    }
}
