package com.cmpe277.garbagemanagementsystem.UserMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.BinID;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.Adapters.BinSearchAdapter;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityBinSearchBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BinSearch extends AppCompatActivity {
    ActivityBinSearchBinding binding;
    ArrayList<DigitalBin> arrayList = new ArrayList<>();
    ArrayList<BinID> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    BinSearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();


        //Search in Recyclerview
        binding.compSearchBin.addTextChangedListener(new TextWatcher() {
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

        adapter = new BinSearchAdapter(this);
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
                            arrayList.add(digitalBin);
                            list.add(new BinID(digitalBin.getBinID()));
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
        ArrayList<BinID> filteredList = new ArrayList<>();
        for (BinID bin : list) {
            if (bin.getBinID().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(bin);
            }
        }
        adapter.filterList(filteredList);
    }
}