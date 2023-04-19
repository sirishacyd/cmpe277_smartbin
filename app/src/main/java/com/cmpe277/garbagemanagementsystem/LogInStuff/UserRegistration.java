package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.baig.garbagemanagementsystem.AccountsManagement.Users;
import com.baig.garbagemanagementsystem.MainActivity;
import com.baig.garbagemanagementsystem.MainScreen.AdminScreen;
import com.baig.garbagemanagementsystem.MainScreen.UserScreen;
import com.baig.garbagemanagementsystem.databinding.ActivityUserRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {
    ActivityUserRegistrationBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //intitialize firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");


        //User registration
        binding.alreadyAccountUser.setOnClickListener(v -> {
            Intent intent = new Intent(UserRegistration.this, UserLogIn.class);
            startActivity(intent);
        });


        /*********
         //User email password Account creation
         */
        binding.registerUser.setOnClickListener(v -> {
            try {
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(binding.userEmail.getText().toString(),
                        binding.userPassword.getText().toString())
                        .addOnCompleteListener(this, task -> {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Users users = new Users(binding.userName.getText().toString(),
                                        binding.userEmail.getText().toString(),
                                        binding.userPassword.getText().toString(),
                                        binding.cnic.getText().toString());
                                String id = task.getResult().getUser().getUid();

                                firebaseDatabase.getReference().child("User").child(id).setValue(users);

                                Toast.makeText(UserRegistration.this, "Created Successfully!",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserRegistration.this, UserScreen.class);
                                startActivity(intent);

                                SharedPreferences.Editor editor = getSharedPreferences("UserLog", MODE_PRIVATE).edit();
                                editor.putBoolean("User_Log_in", false);
                                editor.apply();


                            } else {
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(UserRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(UserRegistration.this, MainActivity.class);
        startActivity(intent);
    }
}