package com.example.myapplication.SOS;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;

import java.util.List;

public class SOS extends AppCompatActivity {

   // private static final int PICK_CONTACT = 1;

    private DatabaseHelper db;
    private List<ContactDesign> list;
    private CustomAdapter customAdapter;
    private Intent intent;
    private FusedLocationProviderClient fusedLocationClient;
    private SmsManager smsManager;
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_o_s);

        //Check for GPS, SMS, and Contact permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS,Manifest.permission.READ_CONTACTS}, 100);
            }
        }

        Button add_contacts_button = findViewById(R.id.Button1);
        Button sms_button = findViewById(R.id.Button2);
        ListView listView = findViewById(R.id.ListView);
        db=new DatabaseHelper(this);
        list=db.getAllContacts();
        customAdapter=new CustomAdapter(this,list);
        listView.setAdapter(customAdapter);

        //Link back_button to close existing activity and return to previous activity
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> finish());

        //Link add_contacts_button to Phone Contacts App
        add_contacts_button.setOnClickListener(v -> {
            //Call getContacts()
            if(db.count()!=5) {
                intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                contactActivityResultLauncher.launch(intent);
            }else{
                Toast.makeText(SOS.this, "Can't Add more than 5 Contacts!", Toast.LENGTH_SHORT).show();
            }
        });

        sms_button.setOnClickListener(v -> {
            //Get current GPS coordinates
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, new CancellationToken() {

                @Override
                public boolean isCancellationRequested() {
                    return false;
                }


                @Override
                @NonNull
                public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                    return null;
                }

                //Check if GPS coordinates are available
            }).addOnSuccessListener(location -> {

                //If GPS coordinates ARE available, send SMS to list of selected contacts with current GPS coordinates.
                if(location!=null){
                    smsManager = SmsManager.getDefault();
                    db=new DatabaseHelper(SOS.this);
                    list=db.getAllContacts();
                    for(ContactDesign c: list){
                        message = "URGENT MESSAGE! \n\n"+c.getContactName()+", I may be in DANGER and need IMMEDIATE ASSISTANCE! \n\nHere are my coordinates.\n "+"http://maps.google.com/?q=" + location.getLatitude() + "," + location.getLongitude();
                        smsManager.sendTextMessage(c.getPhoneNumber(), null, message, null, null);
                    }
                    //Else if GPS coordinates are NOT available, send SMS to list of selected contacts, without GPS coordinates
                }else{
                    message= "URGENT MESSAGE! I may be in DANGER and need IMMEDIATE ASSISTANCE!\n"+"THE GPS was turned off and could not find location. Call your nearest Police Station.";
                    smsManager = SmsManager.getDefault();
                    db=new DatabaseHelper(SOS.this);
                    list=db.getAllContacts();
                    for(ContactDesign c: list){
                        smsManager.sendTextMessage(c.getPhoneNumber(), null, message, null, null);
                    }
                }
                //If failed, Send SMS to list of selected contacts, without GPS coordinates.
            }).addOnFailureListener(e -> {
                Log.d("Check: ","OnFailure");
                message= "URGENT MESSAGE! I may be in DANGER and need IMMEDIATE ASSISTANCE!\n"+"THE GPS was turned off and could not find location. Call your nearest Police Station.";
                smsManager = SmsManager.getDefault();
                db=new DatabaseHelper(SOS.this);
                list=db.getAllContacts();
                for(ContactDesign c: list){
                    smsManager.sendTextMessage(c.getPhoneNumber(), null, message, null, null);
                } });
            //If contact list is empty, display Error Dialog
            if(db.count()==0) {
                openErrorDialog();
                //Else if contact list contains contact(s), display Text Confirmation Dialog
            }else openTextDialog();
        });
    }

    public void openErrorDialog(){
        EmptyContactDialog emptyContactDialog = new EmptyContactDialog();
        //Display emptyContactDialog message
        emptyContactDialog.show(getSupportFragmentManager(), "dialog");
    }
    public void openTextDialog() {
        TextConfirmationDialog textConfirmationDialog = new TextConfirmationDialog();
        //Display textConfirmationDialog message
        textConfirmationDialog.show(getSupportFragmentManager(), "dialog");
    }

    //Launch Phone Contacts Activity
    private final ActivityResultLauncher<Intent> contactActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {

                        @Override
                        public void onActivityResult (ActivityResult resultCode){

                    if (resultCode.getResultCode() == Activity.RESULT_OK) {
                        //Get Selected Contact Info
                        Intent data = resultCode.getData();
                        assert data != null;
                        Uri contactData = data.getData();
                        Cursor c = getContentResolver().query(contactData, null, null, null, null);
                        if (c.moveToFirst()) {

                            String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                            String hasPhone = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                            String phone = null;
                            try {
                                if (hasPhone.equalsIgnoreCase("1")) {
                                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                                    phones.moveToFirst();
                                    phone = phones.getString(phones.getColumnIndexOrThrow("data1"));
                                    phones.close();
                            }
                                String contactName = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                                db.addContact(new ContactDesign(0, contactName, phone));
                                list = db.getAllContacts();
                                customAdapter.refresh(list);
                            }catch (Exception ignored) {
                            }
                        }
                        c.close();
                    }
                }
            });
    }