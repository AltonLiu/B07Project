package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;

public class DisplayEventActivity extends AppCompatActivity {
    TextView startTime, endTime, location, sport, name;
    Button status;
    Customer customerObject;
    Event e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        //get passed object
        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        e = (Event) getIntent().getSerializableExtra("event");
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
        if(customerObject.getScheduled().contains(e)){
            status.setText("scheduled");
        }
        else if(customerObject.getJoined().contains(e)){
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
                        Toast.makeText(getApplicationContext(),"already scheduled",Toast.LENGTH_SHORT).show();
                        break;
                    case "joined":
                        Toast.makeText(getApplicationContext(),"already joined",Toast.LENGTH_SHORT).show();
                        break;
                    case "join":
                        customerObject.join_event(e);
                        Toast.makeText(getApplicationContext(),"joined!",Toast.LENGTH_SHORT).show();
                        status.setText("joined");
                        break;
                }
            }
        });
        }
}