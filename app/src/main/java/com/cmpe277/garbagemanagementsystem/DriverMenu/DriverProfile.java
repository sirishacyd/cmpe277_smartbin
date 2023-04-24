package com.cmpe277.garbagemanagementsystem.DriverMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.cmpe277.garbagemanagementsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverProfile extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private TextView mNameTextView, mEmailTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        mNameTextView = findViewById(R.id.name);
        mEmailTextView = findViewById(R.id.email);

        mAuth = FirebaseAuth.getInstance();

        String driver_uid = mAuth.getCurrentUser().getUid();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Drivers").child(driver_uid);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("userName").getValue(String.class);
                String email = snapshot.child("emailAddress").getValue(String.class);

                mNameTextView.setText(name);
                mEmailTextView.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}