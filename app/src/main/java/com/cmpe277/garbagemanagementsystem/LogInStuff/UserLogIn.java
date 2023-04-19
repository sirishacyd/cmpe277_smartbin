package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baig.garbagemanagementsystem.MainActivity;
import com.baig.garbagemanagementsystem.MainScreen.UserScreen;
import com.baig.garbagemanagementsystem.databinding.ActivityUserLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserLogIn extends AppCompatActivity {
    ActivityUserLoginBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Sign In");
        progressDialog.setMessage("Please wait!");


        // text view registration
        binding.registration.setOnClickListener(v -> {
            Intent intent = new Intent(UserLogIn.this, UserRegistration.class);
            startActivity(intent);
        });


        /*
        User Login stuff will go bellow :)
         */
        binding.userLogIn.setOnClickListener(v -> {
            progressDialog.show();
            try {
                mAuth.signInWithEmailAndPassword(binding.emailInput.getText().toString(),
                        binding.passwordInput.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(UserLogIn.this, "signInWithEmail:success",
                                        Toast.LENGTH_SHORT).show();


                                SharedPreferences.Editor editor = getSharedPreferences("Log",MODE_PRIVATE).edit();
                                editor.putString("value","user");
                                editor.apply();

                                /******
                                 *UI to be updated after the activity is created!
                                 */
                                Intent intent = new Intent(UserLogIn.this, UserScreen.class);
                                startActivity(intent);

                            } else {

                                progressDialog.dismiss();

                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(UserLogIn.this, task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
            } catch (Exception e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Please All boxes!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserLogIn.this, MainActivity.class);
        startActivity(intent);
    }
}