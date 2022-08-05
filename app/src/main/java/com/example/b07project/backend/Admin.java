package com.example.b07project.backend;

import com.google.firebase.database.DatabaseReference;

import java.util.*;

public class Admin extends User {
    public Admin() {
        // Default constructor required for calls to DataSnapshot.getValue(Admin.class)
        super();
    }

    public Admin (String username, String firstname, String lastname, String password, String email) {
        super(username, firstname, lastname, password, "Admin", email);
    }

    /**
     * @param name Name of the venue
     * @param address Address of the venue
     * @param sports An array list of the activities available at the venue
     */
    public void createVenue (DatabaseReference mDatabase, String name, String address, ArrayList<Sport> sports) {
        Venue newVenue = new Venue(name, address, sports);

        // Uploads newVenue to database of all Venues on the server
        mDatabase.child("venues").child(name).setValue(newVenue);
    }

    public int getVenues(){
        //pull all venues from database and store in ArrayList
        return 0;
    }

    public int filterVenues(){
        //call getVenues and filter through ArrayList
        return 0;
    }

}
