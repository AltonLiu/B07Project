package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] customer_menu = {"select", "joined", "scheduled", "upcoming"};
    Customer customerObject;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userRef = mDatabase.child("Users");
    private Button list_venues_button;
    String username;
    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customer_menu);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        username = customerObject.getUsername();


        list_venues_button = (Button) findViewById(R.id.list_venues_button);
        list_venues_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMainActivity.this, DisplayVenueListActivity.class);
                //intent.putExtra("adminObject", adminObject);
                startActivity(intent);
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        select = parent.getItemAtPosition(pos).toString();
        // prepare the customer data
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer customer = dataSnapshot.child(username).getValue(Customer.class);
                Intent intent;
                // An item was selected, start the selected activity
                switch(select) {
                    case "select":
                        break;
                    case "scheduled":
                        intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                        intent.putExtra("customerObject", customer);
                        intent.putExtra("displayType", "scheduled");
                        startActivity(intent);
                        break;
                    case "joined":
                        intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                        intent.putExtra("customerObject", customer);
                        intent.putExtra("displayType", "joined");
                        startActivity(intent);
                        break;
                    case "upcoming":
                        intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                        intent.putExtra("customerObject", customer);
                        intent.putExtra("displayType", "upcoming");
                        startActivity(intent);
                        break;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        userRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // interface callback method, no actual implements
    }
}