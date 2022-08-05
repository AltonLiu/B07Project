package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.b07project.R;
import com.example.b07project.backend.Admin;

public class AdminLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);
        System.out.println("Admin Landing Page");

        // Get the Intent that started this activity and extract the string
        Admin adminObject = (Admin) getIntent().getSerializableExtra("adminObject");

        // Tests
//        System.out.println(adminObject.getUsername());
//        Intent intent = new Intent(this, VenueCreationActivity.class);
//        intent.putExtra("adminObject", adminObject);
//        startActivity(intent);
    }
}