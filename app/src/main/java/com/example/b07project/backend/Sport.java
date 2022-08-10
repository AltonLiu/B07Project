package com.example.b07project.backend;

import java.io.Serializable;

public class Sport implements Serializable {
    private String name;
    private int numParticipants;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }
    
}
