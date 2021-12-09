package com.example.myapplication.iceinfo.medications;

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

public class MedicationsCustomAdapter extends ArrayAdapter<MedicationsDesign> {

    private final Context context;
    private final List<MedicationsDesign> medications_list;
    private MedicationsDatabaseHelper m_db;

    public MedicationsCustomAdapter(@NonNull Context context, List<MedicationsDesign> medications_list) {
        super(context, 0, medications_list);
        this.context=context;
        this.medications_list=medications_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        m_db=new MedicationsDatabaseHelper(context);
        MedicationsDesign c = getItem(position);

        //Link Adapter to item_medications layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_medications, parent, false);
        }

        //Instantiate layout features
        LinearLayout linearLayout= convertView.findViewById(R.id.linear);
        TextView tvMedication = convertView.findViewById(R.id.tvMedication);

        //Add formatted data to database to medications list
        tvMedication.setText(c.getMedication());

        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete medication
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Medication")
                    .setMessage("Are you sure want to remove this medication?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        m_db.deleteMedication(c);
                        medications_list.remove(c);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Medication has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });

        //Display medication(s) to phone screen
        return convertView;
    }

    //Update ListView
    public void refresh(List<MedicationsDesign> list){
        medications_list.clear();
        medications_list.addAll(list);
        notifyDataSetChanged();
    }
}
