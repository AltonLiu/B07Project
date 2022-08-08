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
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayEventListActivity extends AppCompatActivity {
    ArrayList<Event> display_events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_eventlist);
        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        String displayType = (String) getIntent().getStringExtra("displayType");
        LinearLayout ln = (LinearLayout) this.findViewById(R.id.linearlayout);
        switch(displayType){
            case "joined":
                display_events = customerObject.getJoined();
                break;
            case "scheduled":
                display_events = customerObject.getScheduled();
                break;
            case "upcoming":
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference eventsRef = mDatabase.child("Events");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        display_events = new ArrayList<Event>();
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Event event = ds.getValue(Event.class);
                            display_events.add(event);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                eventsRef.addListenerForSingleValueEvent(eventListener);
                break;
        }
        for(Event e: display_events){
            Button button = new Button(this);
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