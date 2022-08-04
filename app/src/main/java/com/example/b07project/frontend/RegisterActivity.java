package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.b07project.R;

import com.example.b07project.backend.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}