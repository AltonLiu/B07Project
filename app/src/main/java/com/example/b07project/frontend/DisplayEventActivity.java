package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayEventActivity extends AppCompatActivity {
    TextView startTime, endTime, location, sport, name;
    Button status;
    Customer customerObject;
    Customer customer;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRef;
    Event e;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        //get passed object
        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        username = customerObject.getUsername();
        mRef = mDatabase.child("Users").child(username);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                e = (Event) getIntent().getSerializableExtra("event");
                customer = snapshot.getValue(Customer.class);
                startTime = (TextView) findViewById(R.id.eventstarttime);
                endTime = (TextView) findViewById(R.id.eventendtime);
                location = (TextView) findViewById(R.id.eventvenue);
                sport = (TextView) findViewById(R.id.eventsport);
                name = (TextView) findViewById(R.id.eventname);
                status = (Button) findViewById(R.id.eventstatus);
                startTime.setText(String.valueOf(e.getStart_time()));
                endTime.setText(String.valueOf(e.getEnd_time()));
                location.setText(e.getLocation().getName());
                sport.setText(e.getSport_type().getName());
                name.setText(e.getName());
                if(customer.getJoined().contains(e)){
                    status.setText("joined");
                }
                else{
                    status.setText("join");
                }
                status.setOnClickListener(new View.OnClickListener() {
                    //start displayEventActivity when the button onclick
                    public void onClick(View view) {
                        switch(status.getText().toString()){
                            case "scheduled":
                            case "join":
                                if(customer.getJoined().contains(e)){
                                    break;
                                }
                                customer.join_event(e);
                                mDatabase.child("Users").child(customerObject.getUsername()).child("joined").setValue(customer.getJoined());
                                Toast.makeText(getApplicationContext(),"joined!",Toast.LENGTH_SHORT).show();
                                status.setText("joined");
                                break;
                            case "joined":
                                Toast.makeText(getApplicationContext(),"already joined",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }
        });
        }
}