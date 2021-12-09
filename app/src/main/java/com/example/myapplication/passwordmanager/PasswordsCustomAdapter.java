package com.example.myapplication.passwordmanager;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class PasswordsCustomAdapter extends ArrayAdapter<PasswordDesign> {

    private final Context context;
    private final List<PasswordDesign> passwords;
    private PasswordsDatabaseHelper db;

    public PasswordsCustomAdapter(Context context, List<PasswordDesign> passwords) {
        super(context, 0, passwords);
        this.context=context;
        this.passwords=passwords;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        db=new PasswordsDatabaseHelper(context);
        PasswordDesign c = getItem(position);

        //Link Adapter to item_passwords layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_passwords, parent, false);
        }

        //Instantiate layout features
        LinearLayout linearLayout= convertView.findViewById(R.id.linear);
        TextView tvApp = convertView.findViewById(R.id.tvApp);
        TextView tvUsername = convertView.findViewById(R.id.tvUsername);
        TextView tvPassword = convertView.findViewById(R.id.tvPassword);

        //Add formatted data to database to passwords list
        tvApp.setText(c.getAppName());
        tvApp.setPaintFlags(tvApp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvUsername.setText(c.getUsername());
        tvPassword.setText(c.getPassword());

        linearLayout.setOnLongClickListener(view -> {

            //Set Long Click to delete Password info
            new MaterialAlertDialogBuilder(context)
                    .setTitle("Remove Password Info")
                    .setMessage("Are you sure want to remove this password?")
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        //delete the specified contact from the database
                        db.deletePassword(c);
                        //remove the item from the list
                        passwords.remove(c);
                        //notify the listview that dataset has been changed
                        notifyDataSetChanged();
                        Toast.makeText(context, "Password has been removed!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });
        //Display password info to phone screen
        return convertView;
    }

    //Update ListView
    public void refresh(List<PasswordDesign> list){
        passwords.clear();
        passwords.addAll(list);
        notifyDataSetChanged();
    }
}

