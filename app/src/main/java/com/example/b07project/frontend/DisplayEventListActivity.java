package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Sport;
import com.example.b07project.backend.Venue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayEventListActivity extends AppCompatActivity {
    ArrayList<Event> display_events;
    Context cur = this;
    Customer customerObject;
    LinearLayout ln;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mRef;
    String displayType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_eventlist);
        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        ln = (LinearLayout) this.findViewById(R.id.linearlayout);
        displayType = (String) getIntent().getStringExtra("displayType");
        switch(displayType){
            case "scheduled":
                mRef = mDatabase.child("Users").child(customerObject.getUsername()).child("scheduled");
                break;
            case "joined":
                mRef = mDatabase.child("Users").child(customerObject.getUsername()).child("joined");
                break;
            case "upcoming":
                mRef = mDatabase.child("Events");
                break;
        }
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ln.removeAllViews();
                display_events = new ArrayList<>();
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    Event e = childSnapshot.getValue(Event.class);
                    if(!display_events.contains(e)){
                        display_events.add(e);}
                }

                if(displayType.equals("scheduled") || displayType.equals("joined")){
                    display_events.remove(0);
                }

                customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
                create_buttons();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }
        });
    }

    public void create_buttons(){
        for(Event e: display_events){
            Button button = new Button(cur);
            //use event.name.hashcode() to assign buttonId
            button.setText(e.getName());
            button.setLayoutParams(new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                //start displayEventActivity when the button onclick
                public void onClick(View view) {
                    Intent is = new Intent(DisplayEventListActivity.this, DisplayEventActivity.class);
                    is.putExtra("customerObject", customerObject);
                    is.putExtra("event", e);
                    startActivity(is);}
            });
            ln.addView(button);
        }
    }

}