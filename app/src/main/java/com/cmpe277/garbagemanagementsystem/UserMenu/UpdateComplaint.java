package com.cmpe277.garbagemanagementsystem.UserMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.Adapters.BinAdapter;
import com.cmpe277.garbagemanagementsystem.Adapters.RegCompAdapter;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityUpdateBinBinding;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityUpdateComplaitBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateComplaint extends AppCompatActivity {
    ActivityUpdateComplaitBinding binding;
    ArrayList<Complaints> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    RegCompAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateComplaitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();


        adapter = new RegCompAdapter(this);
        binding.compRecycler.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.compRecycler.setLayoutManager(linearLayoutManager);
        adapter.Bind(list);
        binding.compRecycler.addItemDecoration(
                new DividerItemDecoration(binding.compRecycler.getContext(),
                        DividerItemDecoration.VERTICAL));

        firebaseDatabase.getReference().child("Complaints")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Complaints complaints = dataSnapshot.getValue(Complaints.class);
                            list.add(complaints);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("this", "loadPost:onCancelled", error.toException());
                    }
                });

    }
}