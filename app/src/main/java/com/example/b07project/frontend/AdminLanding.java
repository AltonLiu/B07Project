package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;

import org.w3c.dom.Text;

public class AdminLanding extends AppCompatActivity {
    private Button venueCreationButton;
    private Button filter_events_button;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);
        venueCreationButton = (Button) findViewById(R.id.venueCreationButton);
        filter_events_button = (Button) findViewById(R.id.filter_events_button);
        name = (TextView) findViewById(R.id.textView3);

        // Get the Intent that started this activity and extract the string
        Admin adminObject = (Admin) getIntent().getSerializableExtra("adminObject");
        name.setText("Welcome, " +  adminObject.getUsername());
        venueCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLanding.this, VenueCreationActivity.class);
                intent.putExtra("adminObject", adminObject);
                startActivity(intent);
            }
        });
        filter_events_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLanding.this, FilterEventActivity.class);
                //intent.putExtra("adminObject", adminObject);
                startActivity(intent);
            }
        });
    }
}