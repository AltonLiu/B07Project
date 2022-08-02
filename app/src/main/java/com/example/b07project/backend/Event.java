package com.example.b07project.backend;

public class Event{
    String name;
    long start_time;
    long end_time;
    Venue location;
    Sport sport_type;
    public Event(String name, long start, long end, Venue location, Sport sport){
        this.name = name;
        this.start_time = start;
        this.end_time = end;
        this.location = location;
        this.sport_type = sport;
    }
}