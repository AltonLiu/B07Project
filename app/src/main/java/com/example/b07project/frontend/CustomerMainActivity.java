package com.example.b07project.frontend;

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

public class CustomerMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] customer_menu = {"select", "joined", "scheduled", "upcoming"};
    private Button list_venues_button;


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

        /*
        list_venues_button = (Button) findViewById(R.id.list_venues_button);
        list_venues_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerMainActivity.this, VenueCreationActivity.class);
                intent.putExtra("adminObject", adminObject);
                startActivity(intent);
            }
        });
        */
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected, start the selected activity
        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        final Intent intent;
        switch(parent.getItemAtPosition(pos).toString()) {
            case "select":
                break;
            case "scheduled":
                intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                intent.putExtra("customerObject", customerObject);
                intent.putExtra("displayType", "scheduled");
                startActivity(intent);
                break;
            case "joined":
                intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                intent.putExtra("customerObject", customerObject);
                intent.putExtra("displayType", "joined");
                startActivity(intent);
                break;
            case "upcoming":
                intent = new Intent(CustomerMainActivity.this, DisplayEventListActivity.class);
                intent.putExtra("displayType", "upcoming");
                startActivity(intent);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // interface callback method, no actual implements
    }
}