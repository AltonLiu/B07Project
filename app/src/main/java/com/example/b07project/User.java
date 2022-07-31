package com.example.b07project;

public abstract class User {
    String username;
    String password;
    String userType;

    public User (String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}
