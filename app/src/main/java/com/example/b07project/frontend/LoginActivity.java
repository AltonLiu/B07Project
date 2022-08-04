package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.*;

import com.google.android.gms.tasks.*;
import com.google.firebase.database.*;
import com.example.b07project.backend.*;
import java.util.*;
import android.os.Bundle;
import com.example.b07project.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // On button press (if login is successful, validateLogin method will redirect user)
            // Call validate login
            validateLogin("AltonL", "12345");
            System.out.println("log in unsuccessful");

            // TODO: Figure out how to detect a failed login in order to display failed login message
            // - Might need to trigger failed login message inside validateLogin

    }

    public void validateLogin(String username, String password) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = mDatabase.child("users").child(username);

        // Check account type of the user and read the server data accordingly
        userRef.child("userType").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                // If the user's account type is null, that means that username doesn't exist
                if (!task.isSuccessful() || task.getResult().getValue() == null) {
                    return;
                }

                String userType = String.valueOf(task.getResult().getValue());

                // Initialize the ValueEventListener (to be added to userRef later)
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Note: dataSnapshot is a snapshot of the user's object on the server
                        // Cast the object as an Admin or Customer depending on userType
                        if (userType.equals("Admin")) {
                            Admin adminObject = dataSnapshot.getValue(Admin.class);
                            if (adminObject.getPassword().equals(password)) {
                                // TODO: Figure out how to pass the adminObject to next activity
                                // - Look at Android tutorial for creating a basic app

                                //Intent intent = new Intent(this, AdminLanding.class);
                                //startActivity(intent);
                                System.out.println("log in successful");
                            }
                        } else {
                            Customer customerObject = dataSnapshot.getValue(Customer.class);
                            if (customerObject.getPassword().equals(password)) {
                                System.out.println("log in successful");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                };
                userRef.addListenerForSingleValueEvent(valueEventListener);
            }
        });
    }
}