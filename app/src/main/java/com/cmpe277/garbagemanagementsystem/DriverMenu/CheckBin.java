package com.cmpe277.garbagemanagementsystem.DriverMenu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.Adapters.BinAdapter;
import com.cmpe277.garbagemanagementsystem.Adapters.CheckBinAdapter;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityCheckBinBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckBin extends AppCompatActivity {
    ActivityCheckBinBinding binding;
    ArrayList<DigitalBin> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    CheckBinAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckBinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();


        //Search in Recyclerview
        binding.checkBin.addTextChangedListener(new TextWatcher() {
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

        adapter = new CheckBinAdapter(this);
        binding.binDetails.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.binDetails.setLayoutManager(linearLayoutManager);
        adapter.Bind(list);
        binding.binDetails.addItemDecoration(new DividerItemDecoration(binding.binDetails.getContext(),
                DividerItemDecoration.VERTICAL));

        firebaseDatabase.getReference().child("Digital Bin")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            DigitalBin digitalBin = dataSnapshot.getValue(DigitalBin.class);
                            list.add(digitalBin);
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
        ArrayList<DigitalBin> filteredList = new ArrayList<>();
        for (DigitalBin bin : list) {
            if (bin.getDriverEmail().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(bin);
            }
        }
        adapter.filterList(filteredList);
    }
}