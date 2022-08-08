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
import com.example.b07project.backend.Event;

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