package com.example.b07project.frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;
import com.example.b07project.backend.Customer;
import com.example.b07project.backend.Event;
import com.example.b07project.backend.Venue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FilterEventActivity extends AppCompatActivity {
    List<Venue> venlist;

    Context cur = this;

    //Admin adminObject;
    LinearLayout ln;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference venref = mDatabase.child("Venues");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_landing);
        //adminObject = (Admin) getIntent().getSerializableExtra("adminObject");
        ln = (LinearLayout) this.findViewById(R.id.linearlayout1);


        venref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //setContentView(R.layout.activity_display_venuelist);
                venlist = new ArrayList<>();

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Venue ven = postSnapshot.getValue(Venue.class);
                    venlist.add(ven);
                    //venue_menu.append(ven.getName());
                    //adminObject = (Admin) getIntent().getSerializableExtra("adminObject");


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
            button.setText(v.getName());
            button.setLayoutParams(new
                    LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));


            button.setOnClickListener(new View.OnClickListener() {
                //start displayEventActivity when the button onclick

                public void onClick(View view) {
                    Intent is = new Intent(FilterEventActivity.this, FilterDisplayActivity.class);
                    is.putExtra("venue", v);
                    startActivity(is);}
            });

            ln.addView(button);
        }
    }

}
