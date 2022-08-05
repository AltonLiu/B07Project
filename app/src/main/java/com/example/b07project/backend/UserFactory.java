package com.example.b07project.backend;

import java.util.ArrayList;

public class UserFactory {

    public User getUser(String userType, String username, String firstname, String lastname, String email, String password){
        ArrayList<Event> scheduled = new ArrayList<Event>();
        ArrayList<Event> joined = new ArrayList<Event>();

        if(userType.equals("Customer")) return new Customer(username, firstname, lastname, password, email, scheduled, joined);
        else if(userType.equals("Admin")) return new Admin(username, firstname, lastname, password, email);
        else return null;


    }
}
