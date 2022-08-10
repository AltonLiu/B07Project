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
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.*;
import android.content.*;

import com.google.android.gms.tasks.*;

import java.util.*;

public class DisplayVenueListActivity extends AppCompatActivity {

    List<Venue> venlist;

    Context cur = this;

    //Admin adminObject;
    LinearLayout ln;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference venref = mDatabase.child("Venues");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_buttons);
        ln = (LinearLayout) this.findViewById(R.id.linearlayout2);


        venref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                venlist = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Venue ven = postSnapshot.getValue(Venue.class);
                    venlist.add(ven);

                }

                create_buttons();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed");
            }
        });
    }

    public void create_buttons(){
        for(Venue v: venlist){
            Button button = new Button(cur);
            //use event.name.hashcode() to assign buttonId
            button.setText(v.toString());
            button.setLayoutParams(new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));


            button.setOnClickListener(new View.OnClickListener() {
                //start displayEventActivity when the button onclick

                public void onClick(View view) {

                    Intent is = new Intent(DisplayVenueListActivity.this, DisplayVenueActivity.class);
                    is.putExtra("venue", v);
                    startActivity(is);}
            });
            ln.addView(button);
        }
    }

}