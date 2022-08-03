package com.example.b07project.backend;

public class Sport {
    String name;
    int numParticipants;

    public Sport() {
        // Default constructor required for FireBase
    }

    public Sport(String name, int numParticipants) {
        this.name = name;
        this.numParticipants = numParticipants;
    }

    public String getName() {
        return name;
    }

    public int getNumParticipants() {
        return numParticipants;
    }
}
