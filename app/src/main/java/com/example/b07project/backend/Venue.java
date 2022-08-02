package com.example.b07project.backend;

import java.util.*;

public class Venue {
    String name;
    String address;
    ArrayList<Sport> sports;

    public Venue(String name, String address, ArrayList<Sport> sports) {
        this.name = name;
        this.address = address;
        this.sports = sports;
    }
}
