package com.cmpe277.garbagemanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.cmpe277.garbagemanagementsystem.DriverMenu.DriverScreen;
import com.cmpe277.garbagemanagementsystem.MainScreen.AdminScreen;
import com.cmpe277.garbagemanagementsystem.MainScreen.UserScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Shared preferences for login activity
        SharedPreferences pref = getSharedPreferences("Log", MODE_PRIVATE);
        String value = pref.getString("value", "");


        new Handler().postDelayed((Runnable) () -> {
            //Do any action here. Now we are moving to next page
            // Check if user is signed in (non-null) and update UI accordingly.

            switch (value) {
                case "admin":
                    Intent intent = new Intent(this, AdminScreen.class);
                    startActivity(intent);
                    break;
                case "user":
                    Intent intent2 = new Intent(this, UserScreen.class);
                    startActivity(intent2);
                    break;
                case "driver":
                    Intent intent3 = new Intent(this, DriverScreen.class);
                    startActivity(intent3);
                    break;
                default:
                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                    break;
            }

            //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
            finish();

        }, 1500);


    }


}