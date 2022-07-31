package com.example.b07project;

public class Event{
    String name;
    long start_time;
    long end_time;
    Venue location;
    Activity sport_type;
    public Event(String name, int num, long start, long end, Venue location, Activity activity){
        this.name = name;
        this.players_num = num;
        this.start_time = start;
        this.end_time = end;
        this.location = loaction;
        this.sport_type = activity;
    }
}