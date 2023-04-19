package com.cmpe277.garbagemanagementsystem.LogInStuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;

import com.baig.garbagemanagementsystem.MainActivity;
import com.baig.garbagemanagementsystem.R;
import com.baig.garbagemanagementsystem.databinding.ActivityRegistrationBinding;

public class Registration extends AppCompatActivity {
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Admin Registration
        binding.adminRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(Registration.this, AdminRegistration.class);
            startActivity(intent);
        });

        //UserRegistration
        binding.userRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(Registration.this, UserRegistration.class);
            startActivity(intent);
        });
    }










    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Registration.this, MainActivity.class);
        startActivity(intent);
    }
}