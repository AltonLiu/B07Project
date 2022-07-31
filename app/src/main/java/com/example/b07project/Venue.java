package com.example.b07project;

import java.util.*;

public class Venue {
    String name;
    String address;
    ArrayList<Activity> activities;

    public Venue(String name, String address, ArrayList<Activity> activities) {
        this.name = name;
        this.address = address;
        this.activities = activities;
    }
}
