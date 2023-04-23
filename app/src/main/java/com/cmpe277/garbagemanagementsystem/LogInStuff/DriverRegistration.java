package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Admin;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.Drivers;
import com.cmpe277.garbagemanagementsystem.MainScreen.AdminScreen;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityAdminRegistrationBinding;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityDriverLogInBinding;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityDriverRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Driver;

public class DriverRegistration extends AppCompatActivity {
    ActivityDriverRegistrationBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDriverRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //intitialize firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");


        //anything updation from drivers details will come here
        binding.emailAddress.setText(getIntent().getStringExtra("email"));
        binding.userName.setText(getIntent().getStringExtra("username"));
        binding.userPassword.setText(getIntent().getStringExtra("password"));
        binding.cnic.setText(getIntent().getStringExtra("cnic"));


        //driver registration button code below
        binding.registerDriver.setOnClickListener(v -> {
            try {
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(binding.emailAddress.getText().toString(),
                        binding.userPassword.getText().toString())
                        .addOnCompleteListener(this, task -> {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Drivers drivers = new Drivers(binding.userName.getText().toString(),
                                        binding.emailAddress.getText().toString(),
                                        binding.userPassword.getText().toString(),
                                        binding.cnic.getText().toString());
                                String id = task.getResult().getUser().getUid();

                                firebaseDatabase.getReference().child("Drivers").child(id).setValue(drivers);

                                Toast.makeText(DriverRegistration.this, "Driver Created Successfully!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DriverRegistration.this, AdminScreen.class);
                                startActivity(intent);


                            } else {
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(DriverRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        });
            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Please Fill All Boxes", Toast.LENGTH_SHORT).show();
            }
        });

    }
}