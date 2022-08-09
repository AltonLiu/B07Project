package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Venue;

import java.util.ArrayList;

public class DisplayVenueListActivity extends AppCompatActivity {

    ArrayList<Venue> display_events = new ArrayList<Venue>();

    /*

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference VenueRef = rootRef.child("Venues");

    ValueEventListener eventListener = new ValueEventListener();

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        ArrayList<Venue> display_events = new ArrayList<Venue>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Venue ven = ds.getKey();
            display_events.add(ven);
        }
    }
    */

    // VenueRef.addListenerForSingleValueEvent(eventListener);

}
