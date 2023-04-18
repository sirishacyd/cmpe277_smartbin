package com.cmpe277.garbagemanagementsystem.UserMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.MainScreen.UserHomeFrag;
import com.cmpe277.garbagemanagementsystem.MainScreen.UserScreen;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityRegisterComplaintBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterComplaint extends AppCompatActivity {
    ActivityRegisterComplaintBinding binding;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterComplaintBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();


        binding.binID.setText(getIntent().getStringExtra("binID"));
        binding.title.setText(getIntent().getStringExtra("title"));
        binding.compDescription.setText(getIntent().getStringExtra("description"));

        binding.binID.isClickable();
        binding.binID.setFocusable(false);
        binding.binID.setText(getIntent().getStringExtra("bin_id"));
        //Show bottom sheet on click
        binding.binID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterComplaint.this, BinSearch.class);
                startActivity(intent);
            }
        });


        //creating instance for firebase database
        binding.submit.setOnClickListener(v -> {
            try {
                if (binding.binID.getText().toString().length() > 0 &&
                        binding.title.getText().toString().length() > 0 &&
                        binding.compDescription.getText().toString().length() > 0) {
                    Complaints complaints = new Complaints(binding.binID.getText().toString(),
                            binding.title.getText().toString(),
                            binding.compDescription.getText().toString(),"Pending");

                    firebaseDatabase.getReference().child("Complaints").child(binding.binID.getText()
                            .toString()).setValue(complaints);
                    Toast.makeText(RegisterComplaint.this, "Complaint Registered!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterComplaint.this, UserScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterComplaint.this,
                            "Please fill All Boxes", Toast.LENGTH_SHORT)
                            .show();
                }
            } catch (DatabaseException e) {
                Toast.makeText(RegisterComplaint.this, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterComplaint.this, UserScreen.class);
        startActivity(intent);
    }
}