package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.*;

import com.google.android.gms.tasks.*;
import com.google.firebase.database.*;
import com.example.b07project.backend.*;
import java.util.*;
import android.os.Bundle;
import com.example.b07project.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tests
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}