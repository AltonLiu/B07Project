package com.example.b07project.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.b07project.R;
import com.example.b07project.backend.*;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}