package com.cmpe277.garbagemanagementsystem.AdminMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.Drivers;
import com.cmpe277.garbagemanagementsystem.Adapters.BinAdapter;
import com.cmpe277.garbagemanagementsystem.Adapters.DriversAdapter;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityViewDriversBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewDrivers extends AppCompatActivity {
    ActivityViewDriversBinding binding;
    ArrayList<Drivers> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DriversAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewDriversBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();


        //Search in Recyclerview
        binding.searchBin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        adapter = new DriversAdapter(this);
        binding.driverDetails.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.driverDetails.setLayoutManager(linearLayoutManager);
        adapter.Bind(list);
        binding.driverDetails.addItemDecoration(new DividerItemDecoration(binding
                .driverDetails.getContext(),
                DividerItemDecoration.VERTICAL));

        //Adding into Arraylist from firebase
        firebaseDatabase.getReference().child("Drivers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Drivers drivers = dataSnapshot.getValue(Drivers.class);
                    list.add(drivers);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("this", "loadPost:onCancelled", error.toException());
            }
        });
    }


    public void filter(String text) {
        ArrayList<Drivers> filteredList = new ArrayList<>();
        for (Drivers drivers : list) {
            if (drivers.getUserName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(drivers);
            }
        }
        adapter.filterList(filteredList);
    }
}