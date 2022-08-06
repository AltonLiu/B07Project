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

public class DisplayScheduledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scheduled);
        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        LinearLayout ln = (LinearLayout) this.findViewById(R.id.linearlayout);
        for(Event e: customerObject.getScheduled()){
            Button button = new Button(this);
            //use event.name.hashcode() to assign buttonId
            button.setText(e.getName());
            button.setLayoutParams(new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(new View.OnClickListener() {
                //start displayEventActivity when the button onclick
                public void onClick(View view) {
                    Intent is = new Intent(DisplayScheduledActivity.this, DisplayEventActivity.class);
                    is.putExtra("event", e);
                    startActivity(is);}
            });
            ln.addView(button);
        }
    }
}