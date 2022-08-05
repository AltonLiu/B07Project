package com.example.b07project.frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b07project.R;
import com.example.b07project.backend.AuthValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.b07project.backend.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private FirebaseAuth mAuth;
    private final String DB_URL = "https://b07-project-ee0dc-default-rtdb.firebaseio.com/";
    private String email, username, firstname, lastname, password, confirmPassword ,usertype;
    private Button registerButton;
    private TextView errorText;
    private EditText emailField, passwordField, confirmPasswordField, firstNameField, lastNameField, usernameField;
    private Spinner userTypeField;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        errorText = (TextView) findViewById(R.id.errorMSG);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        emailField = (EditText) findViewById(R.id.emailField);
        firstNameField = (EditText) findViewById(R.id.firstnameField);
        lastNameField = (EditText) findViewById(R.id.lastnameField);
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        confirmPasswordField = (EditText) findViewById(R.id.confirmPasswordField);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        userTypeField = (Spinner) findViewById(R.id.usertypeField);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.usertypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeField.setAdapter(adapter);

        userTypeField.setOnItemSelectedListener(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailField.getText().toString();
                password = passwordField.getText().toString();
                username = usernameField.getText().toString();
                firstname = firstNameField.getText().toString();
                lastname = lastNameField.getText().toString();
                confirmPassword = confirmPasswordField.getText().toString();

                if(validate(email,password,confirmPassword,username,firstname,lastname)){
                    FirebaseDatabase.getInstance().getReference().child("Users").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // Check if username exists in DB
                            if(!snapshot.exists()){
                                progress.setVisibility(View.VISIBLE);
                                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            // Create Empty Objects for scheduled and joined list to be stored in Firebase
                                            Sport emptySport = new Sport("Empty",0);
                                            Sport emptySport2 = new Sport("Empty",0);
                                            ArrayList<Sport> sportArray = new ArrayList<Sport>();
                                            sportArray.add(emptySport);
                                            sportArray.add(emptySport2);
                                            Venue emptyVenue = new Venue("Empty", "Empty", sportArray);
                                            Event emptyEvent = new Event("Empty", 0,0,emptyVenue, emptySport);

                                            ArrayList<Event> scheduled = new ArrayList<Event>();
                                            ArrayList<Event> joined = new ArrayList<Event>();
                                            scheduled.add(emptyEvent);
                                            joined.add(emptyEvent);

                                            UserFactory u = new UserFactory();
                                            User newUser = u.getUser(usertype, username, firstname, lastname, email, password);

                                            if(usertype.equals("Customer")){
                                                ((Customer) newUser).setScheduled(scheduled);
                                                ((Customer) newUser).setJoined(joined);
                                            }

                                            DatabaseReference ref = FirebaseDatabase.getInstance("https://b07-project-ee0dc-default-rtdb.firebaseio.com/").getReference();
                                            ref.child("Users").child(username).setValue(newUser);

                                            Toast.makeText(RegisterActivity.this, "User has been added", Toast.LENGTH_SHORT).show();
                                            progress.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            try{
                                                throw task.getException();
                                            } catch(FirebaseAuthUserCollisionException e){
                                                progress.setVisibility(View.INVISIBLE);
                                                errorText.setText("Email already exists");
                                                errorText.setVisibility(View.VISIBLE);
                                            } catch (Exception e){
                                                Log.d("Error", e.toString());
                                            }
                                        }
                                    }
                                });
                            } else{
                                progress.setVisibility(View.INVISIBLE);
                                errorText.setText("Username already exists");
                                errorText.setVisibility(View.VISIBLE);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }


    public boolean validate(String email, String password, String confirmPassword ,String username, String firstname, String lastname){
        // verify all fields are non-empty
        String error;

        if(AuthValidation.isEmpty(email) || AuthValidation.isEmpty(password) ||
                AuthValidation.isEmpty(username) || AuthValidation.isEmpty(firstname) ||
                AuthValidation.isEmpty(lastname)){
            error = "Fields must be non-empty";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        // Verify valid name
        if(!AuthValidation.isValidName(firstname) || !AuthValidation.isValidName(lastname)){
            error = "Length of name too small";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        // Verify valid email
        if(!AuthValidation.isValidEmail(email)){
            error = "Email is invalid";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        // Verify valid username
        if(!AuthValidation.isValidUsername(username)){
            error = "Username is invalid";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        // Verify password and confirm password match
        if(!AuthValidation.samePassword(password, confirmPassword)){
            error = "Passwords do not match";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        // Verify valid password
        if(!AuthValidation.isValidPassword(password)){
            error = "Password is invalid";
            errorText.setText(error);
            errorText.setVisibility(View.VISIBLE);
            return false;
        }
        errorText.setText("");
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.usertype = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}