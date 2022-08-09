package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.*;
import android.content.*;

import com.google.android.gms.tasks.*;

import java.util.*;

public class DisplayVenueListActivity extends AppCompatActivity {

    private TextView output;
    public String lines = "";
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference venRef = mDatabase.child("Venues");

    List<Venue> universityList = new ArrayList<>();

    ValueEventListener venlistener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            output = (TextView) findViewById(R.id.textView6);
            universityList.clear();
            lines = "";
            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                Venue ven = postSnapshot.getValue(Venue.class);
                universityList.add(ven);
                lines += ven.toString();

                // here you can access to name property like university.name
            }
            output.setText(lines);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed");
        }
    };
}