package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Detailed view of a business contact
 * Updated from github.com/jmfranz/A4csci3130
 * @author Brianna Phillips
 * @since March 14, 2018
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, txtNumber, txtBusiness, txtAddress, txtProvince;
    Contact businessContact;
    private MyApplicationData appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        businessContact = (Contact)getIntent().getSerializableExtra("Contact");
        appData = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        txtNumber = (EditText) findViewById(R.id.businessNumber);
        txtBusiness = (EditText) findViewById(R.id.business);
        txtAddress = (EditText) findViewById(R.id.address);
        txtProvince = (EditText) findViewById(R.id.province);

        if(businessContact != null){
            nameField.setText(businessContact.name);
            txtNumber.setText(businessContact.businessNumber);
            txtBusiness.setText(businessContact.business);
            txtAddress.setText(businessContact.address);
            txtProvince.setText(businessContact.province);
        }
    }

    /**
     * This method is called when the update
     * button is clicked. This updates the
     * business contact's information.
     * @param v The view is passed as a parameter
     */
    public void updateContact(View v){

        //get stored uid from the business contact
        String uid = businessContact.uid;
        //update the business contact information
        businessContact.name = nameField.getText().toString();
        businessContact.businessNumber = txtNumber.getText().toString();
        businessContact.business = txtBusiness.getText().toString();
        businessContact.address = txtAddress.getText().toString();
        businessContact.province = txtProvince.getText().toString();

        //attempt to set the values in the database.
        //the completion listener will return whether the update was successful or not
        appData.firebaseReference.child(uid).setValue(businessContact, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null)
                    Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_LONG).show();
                else
                    finish();
            }
        });
    }

    /**
     * This method is called when the erase contact
     * button is clicked. This deletes the
     * business contact from the database.
     * @param v The view is passed as a parameter
     */
    public void eraseContact(View v)
    {
        String uid = businessContact.uid;
        appData.firebaseReference.child(uid).setValue(null);
        finish();
    }


}
