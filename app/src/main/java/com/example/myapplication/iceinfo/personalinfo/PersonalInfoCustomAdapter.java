package com.example.myapplication.iceinfo.personalinfo;


import android.content.Context;
import android.graphics.Paint;
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

public class PersonalInfoCustomAdapter extends ArrayAdapter<PersonalInfoDesign> {

    private final Context context;
    private final List<PersonalInfoDesign> personal_info_list;
    private PersonalInfoDatabaseHelper pi_db;

    public PersonalInfoCustomAdapter(@NonNull Context context, List<PersonalInfoDesign> personal_info_list) {
        super(context, 0, personal_info_list);
        this.context=context;
        this.personal_info_list=personal_info_list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        pi_db=new PersonalInfoDatabaseHelper(context);
        PersonalInfoDesign c = getItem(position);

        //Link Adapter to item_personal_info layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_personal_info, parent, false);
        }

        //Instantiate layout features
        LinearLayout linearLayout = convertView.findViewById(R.id.linear);
        TextView tvFirstName = convertView.findViewById(R.id.tvFirstName);
        TextView tvLastName = convertView.findViewById(R.id.tvLastName);
        TextView tvDateOfBirth = convertView.findViewById(R.id.tvDateOfBirth);
        TextView tvAddress = convertView.findViewById(R.id.tvAddress);
        TextView tvCity = convertView.findViewById(R.id.tvCity);
        TextView tvZipcode = convertView.findViewById(R.id.tvZipcode);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        TextView tvInsuranceProvider = convertView.findViewById(R.id.tvInsuranceProvider);
        TextView tvPolicyNumber = convertView.findViewById(R.id.tvPolicyNumber);
        TextView tvBloodType = convertView.findViewById(R.id.tvBloodType);

        //Add formatted data to database to personal info list
        tvFirstName.setText(c.getFirstName());
        tvFirstName.setPaintFlags(tvFirstName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvLastName.setText(c.getLastName());
        tvLastName.setPaintFlags(tvLastName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvDateOfBirth.setText(c.getDateOfBirth());
        tvDateOfBirth.setPaintFlags(tvDateOfBirth.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvAddress.setText(c.getAddress());
        tvAddress.setPaintFlags(tvAddress.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvCity.setText(c.getCity());
        tvCity.setPaintFlags(tvCity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvZipcode.setText(c.getZipcode());
        tvZipcode.setPaintFlags(tvZipcode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvPhoneNumber.setText(c.getPhoneNumber());
        tvPhoneNumber.setPaintFlags(tvPhoneNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvInsuranceProvider.setText(c.getInsuranceProvider());
        tvInsuranceProvider.setPaintFlags(tvInsuranceProvider.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvPolicyNumber.setText(c.getPolicyNumber());
        tvPolicyNumber.setPaintFlags(tvPolicyNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvBloodType.setText(c.getBloodType());
        tvBloodType.setPaintFlags(tvBloodType.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete personal info
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Personal Info")
                    .setMessage("Are you sure want to remove this personal info?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        pi_db.deletePersonalInfo(c);
                        personal_info_list.remove(c);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Personal info has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });
        //Display personal info to phone screen
        return convertView;
    }

    //Update ListView
    public void refresh(List<PersonalInfoDesign> list){
        personal_info_list.clear();
        personal_info_list.addAll(list);
        notifyDataSetChanged();
    }
}
