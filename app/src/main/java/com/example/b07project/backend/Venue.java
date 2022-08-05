package com.example.b07project.backend;

import java.util.*;

public class Venue {
    private String name;
    private String address;
    private ArrayList<Sport> sports;

    public Venue () {
        // Default constructor required for FireBase
    }

    public Venue(String name, String address, ArrayList<Sport> sports) {
        this.name = name;
        this.address = address;
        this.sports = sports;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Sport> getSports() {
        return sports;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSports(ArrayList<Sport> sports) {
        this.sports = sports;
    }
}
