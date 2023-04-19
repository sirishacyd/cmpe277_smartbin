package com.cmpe277.garbagemanagementsystem.AdminMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baig.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.baig.garbagemanagementsystem.MainScreen.AdminHomeFrag;
import com.baig.garbagemanagementsystem.MainScreen.AdminScreen;
import com.baig.garbagemanagementsystem.R;
import com.baig.garbagemanagementsystem.databinding.ActivityCreateBinBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;

public class CreateBin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ActivityCreateBinBinding binding;
    private FirebaseDatabase firebaseDatabase;
    String load_type[] = {"Load Type","Low", "Medium", "High"};
    String cycle_day[] = {"Cycle","Daily", "Weekly", "Monthly"};
    private String loadType = "";
    private String cycleDay = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get reference of database
        firebaseDatabase = FirebaseDatabase.getInstance();

        //loadType Spinner
        binding.loadType.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the load list
        ArrayAdapter aa = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, load_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.loadType.setAdapter(aa);

        //cycle Spinner
        binding.cycle.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the cycle list
        ArrayAdapter cycle = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, cycle_day);
        cycle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.cycle.setAdapter(cycle);


        /*
        get bin data from update bin activity
         */
        binding.binID.setText(getIntent().getStringExtra("bin_id"));
        binding.binAddress.setText(getIntent().getStringExtra("bin_address"));
        binding.city.setText(getIntent().getStringExtra("city_name"));
        binding.driverEmail.setText(getIntent().getStringExtra("driver_email"));
        binding.bestRoute.setText(getIntent().getStringExtra("best_route"));

        /*
        Storing data in firebase database
         */
        binding.addBin.setOnClickListener(v -> {
            try {
                DigitalBin createBin = new DigitalBin(binding.binAddress.getText().toString(),
                        binding.city.getText().toString(),
                        binding.binID.getText().toString(),
                        binding.driverEmail.getText().toString(),
                        binding.bestRoute.getText().toString(), loadType, cycleDay);

                if (binding.binID.getText().toString().length() > 0 &&
                        binding.city.getText().toString().length() > 0 &&
                        binding.binAddress.getText().toString().length() > 0 &&
                        binding.driverEmail.getText().toString().length() > 0 &&
                        cycleDay !="Cycle" &&
                        loadType !="Load Type" &&
                        binding.bestRoute.getText().toString().length() > 0) {
                    firebaseDatabase.getReference().child("Digital Bin").child(binding.binID.getText()
                            .toString()).setValue(createBin);
                    Intent intent = new Intent(CreateBin.this, AdminScreen.class);
                    startActivity(intent);
                    Toast.makeText(this, "Digital Bin Created Successfully",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateBin.this, "Please All Boxes!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(CreateBin.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loadType = load_type[position];
        cycleDay = cycle_day[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(CreateBin.this, "Please select an option!", Toast.LENGTH_SHORT).show();
    }
}