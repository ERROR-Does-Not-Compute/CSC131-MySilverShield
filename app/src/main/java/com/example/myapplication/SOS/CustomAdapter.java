package com.example.myapplication.SOS;

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

public class CustomAdapter extends ArrayAdapter<ContactDesign> {

    private final Context context;
    private final List<ContactDesign> contacts;
    private DatabaseHelper db;

    public CustomAdapter(@NonNull Context context, List<ContactDesign> contacts) {
        super(context, 0, contacts);
        this.context=context;
        this.contacts=contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        db=new DatabaseHelper(context);
        ContactDesign c = getItem(position);

        //Link Adapter to item_contacts layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contacts, parent, false);
        }

        //Instantiate Layout features
        LinearLayout linearLayout = convertView.findViewById(R.id.linear);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        //Add formatted data to database to emergency contacts list
        tvName.setText(c.getContactName());
        tvPhone.setText(c.getPhoneNumber());

        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete a contact from list of emergency contacts
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Contact")
                    .setMessage("Are you sure want to remove this emergency contact?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        db.deleteContact(c);
                        contacts.remove(c);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Contact has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });
        //Display list of emergency contacts to phone screen.
        return convertView;
    }

    //Update ListView
    public void refresh(List<ContactDesign> list){
        contacts.clear();
        contacts.addAll(list);
        notifyDataSetChanged();
    }
}
