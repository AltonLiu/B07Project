package com.example.b07project.backend;

public class Event{
    private String name;
    private long start_time;
    private long end_time;
    private Venue location;
    private Sport sport_type;
    public Event(String name, long start, long end, Venue location, Sport sport){

        this.name = name;
        this.start_time = start;
        this.end_time = end;
        this.location = location;
        this.sport_type = sport;
    }

    public Event(){}

    public long getEnd_time() {
        return end_time;
    }

    public long getStart_time() {
        return start_time;
    }

    public Sport getSport_type() {
        return sport_type;
    }

    public String getName() {
        return name;
    }

    public Venue getLocation() {
        return location;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public void setLocation(Venue location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSport_type(Sport sport_type) {
        this.sport_type = sport_type;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }
}