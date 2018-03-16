package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines the Contact information within
 * the Firebase database.
 * Updated from github.com/jmfranz/A4csci3130
 * @author Brianna Phillips
 * @since March 14, 2018
 */

public class Contact implements Serializable {

    public String uid;
    public String name;
    public String businessNumber;
    public String business;
    public String address;
    public String province;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    //Contact constructor
    public Contact(String uid, String name, String number, String business, String address, String province){
        this.uid = uid;
        this.name = name;
        this.businessNumber = number;
        this.business = business;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("businessNumber", businessNumber);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
