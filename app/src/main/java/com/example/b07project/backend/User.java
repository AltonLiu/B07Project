package com.example.b07project.backend;

import android.os.Parcelable;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String userType;
    private String email;

    // Constructors
    public User() {
        // Default constructor required for FireBase
    }

    public User(String username, String firstname, String lastname, String password, String userType, String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.userType = userType;
        this.email = email;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }
}
