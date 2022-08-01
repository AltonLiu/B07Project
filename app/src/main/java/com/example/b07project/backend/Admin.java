package com.example.b07project.backend;

import java.util.*;

public class Admin extends User {
    public Admin() {
        // Default constructor required for calls to DataSnapshot.getValue(Admin.class)
        super();
    }

    public Admin (String username, String firstname, String lastname, String password) {
        super(username, firstname, lastname, password, "Admin");
    }

    /**
     * @param name Name of the venue
     * @param address Address of the venue
     * @param activities An array list of the activities available at the venue
     */
    public void createVenue (String name, String address, ArrayList<Activity> activities) {
        Venue newVenue = new Venue(name, address, activities);
        // Add code to upload newVenue to array list of all Venues on the server
    }
}
