package com.example.myapplication.iceinfo.allergies;

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

public class AllergiesCustomAdapter extends ArrayAdapter<AllergiesDesign> {

    private final Context context;
    private final List<AllergiesDesign> allergies_list;
    private AllergiesDatabaseHelper a_db;

    public AllergiesCustomAdapter(@NonNull Context context, List<AllergiesDesign> allergies_list) {
        super(context, 0, allergies_list);
        this.context=context;
        this.allergies_list=allergies_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        a_db=new AllergiesDatabaseHelper(context);
        AllergiesDesign c = getItem(position);

        //Link Adapter to item_allergies layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_allergies, parent, false);
        }

        //Instantiate layout features
        LinearLayout linearLayout= convertView.findViewById(R.id.linear);
        TextView tvAllergy = convertView.findViewById(R.id.tvAllergy);

        //Add formatted data to database to allergies list
        tvAllergy.setText(c.getAllergy());


        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete allergy
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Allergy Info")
                    .setMessage("Are you sure want to remove this allergy?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        a_db.deleteAllergy(c);
                        allergies_list.remove(c);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Password has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });

        //Display allergies to phone screen
        return convertView;
    }

    //Update ListView
    public void refresh(List<AllergiesDesign> list){
        allergies_list.clear();
        allergies_list.addAll(list);
        notifyDataSetChanged();
    }
}
