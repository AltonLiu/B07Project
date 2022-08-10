package com.example.b07project.frontend;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FilterDisplayActivity extends AppCompatActivity {

    private TextView output, header;
    public String lines = "";
    public String header_name = "Event list";
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference venref = mDatabase.child("Events");
    List<Event> eventlist;
    Venue ve;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_venuelist);
        output = (TextView) findViewById(R.id.textView6);
        header = (TextView) findViewById(R.id.list_header);
        header.setText(header_name);
        ve = (Venue) getIntent().getSerializableExtra("venue");
        //output.setText(lines);

        venref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //setContentView(R.layout.activity_display_venuelist);
                ve = (Venue) getIntent().getSerializableExtra("venue");
                output = (TextView) findViewById(R.id.textView6);
                eventlist = new ArrayList<>();
                //lines = "";
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Event e = postSnapshot.getValue(Event.class);
                    if(e.getLocation().getName().equals(ve.getName())) {
                        eventlist.add(e);
                    }
                    //System.out.println(ven.toString());

                    // here you can access to name property like university.name
                }
                for(Event line : eventlist){
                    lines+=line.toString();
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