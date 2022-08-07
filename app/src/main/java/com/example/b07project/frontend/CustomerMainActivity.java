package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.b07project.R;
import com.example.b07project.backend.Customer;

public class CustomerMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.customerMenu, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected, start the selected activity
        Customer customerObject = (Customer) getIntent().getSerializableExtra("customerObject");
        if(parent.getItemAtPosition(pos).toString().equals("scheduled")){
            Intent intent = new Intent(this, DisplayScheduledActivity.class);
            intent.putExtra("customerObject", customerObject);
            startActivity(intent);
        }
        else if(parent.getItemAtPosition(pos).toString().equals("joined")){
            Intent intent = new Intent(this, DisplayJoinedActivity.class);
            intent.putExtra("customerObject", customerObject);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, DisplayUpcomingActivity.class);
            startActivity(intent);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // interface callback method, no actual implements
    }
}