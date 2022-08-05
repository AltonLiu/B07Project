package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Sport;
import com.example.b07project.backend.User;
import com.example.b07project.backend.UserFactory;
import com.example.b07project.backend.Venue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VenueCreationActivity extends AppCompatActivity {
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_creation);

        Admin adminObject = (Admin) getIntent().getSerializableExtra("adminObject");
        errorText = (TextView) findViewById(R.id.venueCreationErrorMSG);

        // Test
//        ArrayList<Sport> sports = new ArrayList<>();
//        sports.add(new Sport("Baseball", 18));
//        validateVenue(adminObject, "Test Venue 1", "123 Sesame Street", sports);
    }

    public void validateVenue(Admin adminObject, String name, String address, ArrayList<Sport> sports) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference venuesRef = mDatabase.child("Venues");

        if (name.equals("") || address.equals("") || sports == null || sports.size() == 0) {
            // Display missing field message
            errorText.setText("Missing required field");
            errorText.setVisibility(View.VISIBLE);
            return;
        }

        venuesRef.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if venue name exists in DB
                if(!snapshot.exists()){
                    adminObject.createVenue(mDatabase, name, address, sports);
                } else{
                    errorText.setText("Venue name already exists");
                    errorText.setVisibility(View.VISIBLE);
                    System.out.println("Venue name already exists");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}