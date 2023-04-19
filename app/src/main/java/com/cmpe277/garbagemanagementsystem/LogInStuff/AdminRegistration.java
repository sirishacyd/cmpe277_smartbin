package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Admin;
import com.cmpe277.garbagemanagementsystem.MainActivity;
import com.cmpe277.garbagemanagementsystem.MainScreen.AdminScreen;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityAdminRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminRegistration extends AppCompatActivity {
    ActivityAdminRegistrationBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //intitialize firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");


        // text View Already have an account
        binding.alreadyAccount.setOnClickListener(v -> {
            Intent intent = new Intent(AdminRegistration.this, AdminLogIn.class);
            startActivity(intent);
        });


        //Admin Registration with Email and password
        binding.registerAdmin.setOnClickListener(v -> {
            try {
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(binding.emailAddress.getText().toString(),
                        binding.userPassword.getText().toString())
                        .addOnCompleteListener(this, task -> {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Admin admin = new Admin(binding.userName.getText().toString(),
                                        binding.emailAddress.getText().toString(),
                                        binding.userPassword.getText().toString(),
                                        binding.cnic.getText().toString());
                                String id = task.getResult().getUser().getUid();

                                firebaseDatabase.getReference().child("Admin").child(id).setValue(admin);

                                Toast.makeText(AdminRegistration.this, "Admin Created Successfully!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AdminRegistration.this, AdminScreen.class);
                                startActivity(intent);

                            } else {
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(AdminRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        });
            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Please Fill All Boxes", Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AdminRegistration.this, MainActivity.class);
        startActivity(intent);
    }
}