package com.example.b07project;
import java.util.ArrayList;

public class Customer extends User{
    ArrayList<Event> scheduled;
    ArrayList<Event> joined;

    public Customer(String name, String firstname, String lastname, String pw, ArrayList<Event> sch, ArrayList<Event> joined){
        super(name, firstname, lastname, pw, "Customer");
        this.scheduled = sch;
        this.joined = joined;
    }

    public void schedule_event(Event event){
        this.scheduled.add(event);
    }

    public void join_event(Event event){
        this.joined.add(event);
    }
}
