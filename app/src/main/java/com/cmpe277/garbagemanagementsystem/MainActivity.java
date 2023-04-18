package com.cmpe277.garbagemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cmpe277.garbagemanagementsystem.LogInStuff.AdminLogIn;
import com.cmpe277.garbagemanagementsystem.LogInStuff.DriverLogIn;
import com.cmpe277.garbagemanagementsystem.LogInStuff.UserLogIn;
import com.cmpe277.garbagemanagementsystem.LogInStuff.Registration;
import com.cmpe277.garbagemanagementsystem.LogInStuff.UserRegistration;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        //User Registration
        binding.user.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UserRegistration.class);
            startActivity(intent);
        });

        //Registration Button
        binding.register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Registration.class);
            startActivity(intent);
        });

        //Admin Log In
        binding.admin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdminLogIn.class);
            startActivity(intent);
        });

        //Driver Log In
        binding.driver.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, DriverLogIn.class);
            startActivity(intent);
        });

        // User Log In Page
        binding.user.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, UserLogIn.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}