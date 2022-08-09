package com.example.b07project.frontend;

import androidx.annotation.NonNull;
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
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.*;
import android.content.*;

import com.google.android.gms.tasks.*;

import java.util.*;

public class DisplayVenueListActivity extends AppCompatActivity {

   private TextView output;
    public String lines = "Alina be cute as fuck";
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference venref = mDatabase.child("Venues");
    List<Venue> venlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_venuelist);
        output = (TextView) findViewById(R.id.textView6);
        //output.setText(lines);

        venref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //setContentView(R.layout.activity_display_venuelist);
                output = (TextView) findViewById(R.id.textView6);
                venlist = new ArrayList<>();
                //lines = "";
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Venue ven = postSnapshot.getValue(Venue.class);
                    venlist.add(ven);
                    //lines += ven.toString();
                    //System.out.println(ven.toString());

                    // here you can access to name property like university.name
                }
                for(Venue v : venlist){
                    //lines+=v.toString();
                }
                output.setText(lines);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }
        });
    }

}