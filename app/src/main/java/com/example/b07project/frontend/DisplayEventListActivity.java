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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DisplayEventListActivity extends AppCompatActivity {
    ArrayList<Event> display_events = new ArrayList<>();
    Context cur = this;
    Customer customerObject;
    LinearLayout ln;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference eventsRef = mDatabase.child("Events");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_eventlist);
        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        String displayType = (String) getIntent().getStringExtra("displayType");
        ln = (LinearLayout) this.findViewById(R.id.linearlayout);
        switch(displayType){
            case "joined":
                display_events = customerObject.getJoined();
                create_buttons();
                break;
            case "scheduled":
                display_events = customerObject.getScheduled();
                create_buttons();
                break;
            case "upcoming":
                eventsRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                            Event e = childSnapshot.getValue(Event.class);
                            String e_date = Long.toString(e.getStart_time());
                            LocalDateTime now = LocalDateTime.now();
                            LocalDateTime e_time = LocalDateTime.of(Integer.parseInt(e_date.substring(0,4)),
                                    Integer.parseInt(e_date.substring(4,6)),
                                    Integer.parseInt(e_date.substring(6,8)),
                                    Integer.parseInt(e_date.substring(8,10)),
                                    Integer.parseInt(e_date.substring(10)));
                            if(e_time.isAfter(now)){
                                display_events.add(e);}
                        }
                        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
                        create_buttons();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed");
                    }
                });
                break;
        }
        return;
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