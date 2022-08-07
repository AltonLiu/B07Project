package com.example.b07project.backend;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends User implements Serializable {
    private ArrayList<Event> scheduled;
    private ArrayList<Event> joined;

    public Customer(String username, String firstname, String lastname, String pw, String email, ArrayList<Event> sch, ArrayList<Event> joined){
        super(username, firstname, lastname, pw, "Customer", email);
        this.scheduled = sch;
        this.joined = joined;
    }
    public Customer(){}

    public ArrayList<Event> getJoined() {
        return joined;
    }

    public ArrayList<Event> getScheduled() {
        return scheduled;
    }

    public void setScheduled(ArrayList<Event> scheduled){
        this.scheduled = scheduled;
    }

    public void setJoined(ArrayList<Event> joined){
        this.joined = joined;
    }

    public void schedule_event(Event event){
        this.scheduled.add(event);
    }

    public void join_event(Event event){
        this.joined.add(event);
    }
}
