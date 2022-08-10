package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
        EditText venueNameField = (EditText) findViewById(R.id.venueNameField);
        EditText venueAddressField = (EditText) findViewById(R.id.venueAddressField);
        CheckBox[] checkBoxes = {
                (CheckBox) findViewById(R.id.checkBox5),
                (CheckBox) findViewById(R.id.checkBox6),
                (CheckBox) findViewById(R.id.checkBox7),
                (CheckBox) findViewById(R.id.checkBox8),
                (CheckBox) findViewById(R.id.checkBox9),
                (CheckBox) findViewById(R.id.checkBox10)
        };
        Button validateVenueButton = (Button) findViewById(R.id.validateVenueButton);

        ArrayList<Sport> availableSports = new ArrayList<>();
        availableSports.add(new Sport("Baseball", 18));
        availableSports.add(new Sport("Basketball", 10));
        availableSports.add(new Sport("Ping Pong", 2));
        availableSports.add(new Sport("Soccer", 22));
        availableSports.add(new Sport("Tennis", 4));
        availableSports.add(new Sport("Volleyball", 12));

        validateVenueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = venueNameField.getText().toString();
                String address = venueAddressField.getText().toString();
                ArrayList<Sport> sports = new ArrayList<>();

                // For each box that is checked, add the corresponding sport to the sports arraylist
                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isChecked()) {
                        sports.add(availableSports.get(i));
                    }
                }

                validateVenue(adminObject, name, address, sports);
            }
        });
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
                    Toast.makeText(VenueCreationActivity.this, "Venue has been added", Toast.LENGTH_LONG).show();
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