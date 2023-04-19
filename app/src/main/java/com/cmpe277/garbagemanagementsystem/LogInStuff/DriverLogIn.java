package com.cmpe277.garbagemanagementsystem.LogInStuff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe277.garbagemanagementsystem.DriverMenu.DriverScreen;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityDriverLogInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLogIn extends AppCompatActivity {
    ActivityDriverLogInBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDriverLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sign In");
        progressDialog.setMessage("Please wait!");


        //Driver Login button
        binding.driverlogIn.setOnClickListener(v -> {
            progressDialog.show();
            try {
                mAuth.signInWithEmailAndPassword(binding.emailInput.getText().toString(),
                        binding.passwordInput.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(DriverLogIn.this, "signInWithEmail:success",
                                        Toast.LENGTH_SHORT).show();

                                SharedPreferences.Editor editor = getSharedPreferences("Log",MODE_PRIVATE).edit();
                                editor.putString("value","driver");
                                editor.apply();

                                /*
                                this place needs to be set after the driver home screen is made
                                 */
                                Intent intent = new Intent(DriverLogIn.this,
                                        DriverScreen.class);
                                startActivity(intent);
                            } else {

                                progressDialog.dismiss();

                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(DriverLogIn.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Please All boxes!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}