package com.example.b07project.backend;

public abstract class User {
    String username;
    String firstname;
    String lastname;
    String password;
    String userType;

    // Constructors
    public User() {
        // Default constructor required for FireBase
    }

    public User(String username, String firstname, String lastname, String password, String userType) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.userType = userType;
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
}
