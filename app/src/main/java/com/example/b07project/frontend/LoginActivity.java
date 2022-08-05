package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.*;

import com.google.android.gms.tasks.*;
import com.google.firebase.database.*;
import com.example.b07project.backend.*;
import java.util.*;
import android.os.Bundle;
import android.widget.Toast;

import com.example.b07project.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // On button press (if login is successful, validateLogin method will redirect user, if not
        // then a failed login toast will be displayed)
            // Call validate login
            //validateLogin(username, password);

        // Tests
        //DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        //validateLogin("admin123", "admintest123");
    }

    public void validateLogin(String username, String password) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = mDatabase.child("Users").child(username);

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
                        // Cast the object as an Admin or Customer depending on userType, then
                        // if the password/username combo is correct, switch to customer or admin
                        // activity and pass in the user's object to the next activity
                        if (userType.equals("Admin")) {
                            Admin adminObject = dataSnapshot.getValue(Admin.class);
                            if (adminObject.getPassword().equals(password)) {
                                Intent intent = new Intent(LoginActivity.this, AdminLanding.class);
                                intent.putExtra("adminObject", adminObject);
                                startActivity(intent);
                            } else {
                                // Display login failed toast
                                Toast loginFailedToast = Toast.makeText(getApplicationContext(),
                                        "Incorrect username/password",
                                        Toast.LENGTH_LONG);
                                loginFailedToast.show();
                            }
                        } else {
                            Customer customerObject = dataSnapshot.getValue(Customer.class);
                            if (customerObject.getPassword().equals(password)) {
                                Intent intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                                intent.putExtra("customerObject", customerObject);
                                startActivity(intent);
                            } else {
                                // Display login failed toast
                                Toast loginFailedToast = Toast.makeText(getApplicationContext(),
                                        "Incorrect username/password",
                                        Toast.LENGTH_LONG);
                                loginFailedToast.show();
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