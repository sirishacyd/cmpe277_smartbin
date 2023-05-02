package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cmpe277.garbagemanagementsystem.MainScreen.AdminScreen;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityAdminLogInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogIn extends AppCompatActivity {
    ActivityAdminLogInBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sign In");
        progressDialog.setMessage("Please wait!");


        //Registration?
        binding.registration.setOnClickListener(v -> {
            Intent intent = new Intent(AdminLogIn.this, AdminRegistration.class);
            startActivity(intent);
        });


        //Admin Login Button **********
        binding.adminlogIn.setOnClickListener(v -> {
            progressDialog.show();
            try {
                mAuth.signInWithEmailAndPassword(binding.emailInput.getText().toString(),
                        binding.passwordInput.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();


                                SharedPreferences.Editor editor = getSharedPreferences("Log",MODE_PRIVATE).edit();
                                editor.putString("value","admin");
                                editor.apply();


                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(AdminLogIn.this, "signInWithEmail:success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AdminLogIn.this, AdminScreen.class);
                                startActivity(intent);


                            } else {

                                progressDialog.dismiss();

                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(AdminLogIn.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });

            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Please Enter All Fields!", Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onStart() {
        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(AdminLogIn.this, AdminScreen.class);
//            startActivity(intent);
//        }
    }
}