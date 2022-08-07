package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.backend.Event;

public class DisplayEventActivity extends AppCompatActivity {
    TextView startTime, endTime, location, sport, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        Event e = (Event) getIntent().getSerializableExtra("event");
        startTime = (TextView) findViewById(R.id.eventstarttime);
        endTime = (TextView) findViewById(R.id.eventendtime);
        location = (TextView) findViewById(R.id.eventvenue);
        sport = (TextView) findViewById(R.id.eventsport);
        name = (TextView) findViewById(R.id.eventname);
        startTime.setText(String.valueOf(e.getStart_time()));
        endTime.setText(String.valueOf(e.getEnd_time()));
        location.setText(e.getLocation().getName());
        sport.setText(e.getSport_type().getName());
        name.setText(e.getName());
    }
}