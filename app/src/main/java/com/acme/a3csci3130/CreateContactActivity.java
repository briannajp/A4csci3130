package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Create business contact
 * Updated from github.com/jmfranz/A4csci3130
 * @author Brianna Phillips
 * @since March 14, 2018
 */
public class CreateContactActivity extends Activity {

    private Button submitButton;
    private EditText nameField, txtNumber, txtBusiness, txtAddress, txtProvince;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_activity);

        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        txtNumber = (EditText) findViewById(R.id.businessNumber);
        txtBusiness = (EditText) findViewById(R.id.business);
        txtAddress = (EditText) findViewById(R.id.address);
        txtProvince = (EditText) findViewById(R.id.province);
    }

    /**
     * This method is called when the submit
     * button is clicked. This adds the
     * business contact to the database.
     * @param v The view is passed as a parameter
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String businessID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String number = txtNumber.getText().toString();
        String businessType = txtBusiness.getText().toString();
        String address = txtAddress.getText().toString();
        String province = txtProvince.getText().toString().toUpperCase();
        Contact business = new Contact(businessID, name, number, businessType, address, province);

        //set the values in the database -- the completion listener
        //will return whether or not the creation was successful.
        appState.firebaseReference.child(businessID).setValue(business, new DatabaseReference.CompletionListener() {
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
     * This method is called when the cancel
     * button is clicked. This cancels the
     * contact creation.
     * @param v The view is passed as a parameter
     */
    public void cancelButtonPressed(View v) {
        finish();
    }


}
