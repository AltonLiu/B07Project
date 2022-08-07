package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;

public class CustomerMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] customer_menu = {"select", "joined", "scheduled", "upcoming"};


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
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected, start the selected activity
        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        final Intent intent;
        switch(parent.getItemAtPosition(pos).toString()) {
            case "select":
                break;
            case "scheduled":
                intent = new Intent(CustomerMainActivity.this, DisplayScheduledActivity.class);
                intent.putExtra("customerObject", customerObject);
                startActivity(intent);
                break;
            case "joined":
                intent = new Intent(CustomerMainActivity.this, DisplayJoinedActivity.class);
                intent.putExtra("customerObject", customerObject);
                startActivity(intent);
                break;
            case "upcoming":
                intent = new Intent(CustomerMainActivity.this, DisplayUpcomingActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // interface callback method, no actual implements
    }
}