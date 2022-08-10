package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Sport;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayVenueActivity extends AppCompatActivity {
    TextView header;
    Customer customerObject;
    Venue v;
    Context cur = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);

        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        Venue v = (Venue) getIntent().getSerializableExtra("venue");
        ArrayList<Sport> v_sports = v.getSports();

        header = (TextView) findViewById(R.id.schedule_header);
        header.setText("To schedule event at this venue fill times and choose 1 sport. Times must be of format (YYYYMMDDhhmm). Sport must be name of sport existing under selected venue.");

        EditText event_name = (EditText) findViewById(R.id.set_event_name);
        EditText event_start = (EditText) findViewById(R.id.set_start_time);
        EditText event_end = (EditText) findViewById(R.id.set_end_time);
        EditText set_sport = (EditText) findViewById(R.id.set_sport);
        Button send_button = (Button) findViewById(R.id.done_button);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_string = event_name.getText().toString();
                String start_string = event_start.getText().toString();
                long start_long = Long.parseLong(start_string);
                String end_string = event_end.getText().toString();
                long end_long = Long.parseLong(end_string);
                String sport_string = set_sport.getText().toString();
                Sport selected_sport = null;

                // For each box that is checked, add the corresponding sport to the sports arraylist
                for (Sport s : v_sports) {
                    if (s.getName().equals(sport_string)) {
                        selected_sport = s;
                    }
                }

                validateVenue(customerObject, name_string, start_long, end_long, v, selected_sport);
            }
        });
    }

    public void validateVenue(Customer co, String name, long start, long end, Venue v, Sport s) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference venuesRef = mDatabase.child("Events");

        venuesRef.child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Check if venue name exists in DB
                if(!snapshot.exists()){
                    Event new_e = new Event(name, start, end, v, s);
                    mDatabase.child("Events").child(name).setValue(new_e);
                    //send event to database
                    //customer set scheduled event
                    co.schedule_event(new_e);
                    mDatabase.child("Users").child(co.getUsername()).setValue(co);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        /*
        Event new_e = new Event(name, start, end, v, s);
        mDatabase.child("Events").child(name).setValue(new_e);
        //send event to database
        //customer set scheduled event
        co.schedule_event(new_e);
        mDatabase.child("Users").child(co.getUsername()).setValue(co);

         */


    }
}
