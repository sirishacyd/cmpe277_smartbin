package com.cmpe277.garbagemanagementsystem.AdminMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.WorkReport;
import com.cmpe277.garbagemanagementsystem.Adapters.BinAdapter;
import com.cmpe277.garbagemanagementsystem.Adapters.WorkAdapter;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityWorkDetailsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WorkDetails extends AppCompatActivity {
    ActivityWorkDetailsBinding binding;
    ArrayList<WorkReport> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    WorkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();


        //Search in Recyclerview
        binding.searchDetails.addTextChangedListener(new TextWatcher() {
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

        adapter = new WorkAdapter(this);
        binding.workDetails.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.workDetails.setLayoutManager(linearLayoutManager);
        binding.workDetails.addItemDecoration(new DividerItemDecoration(binding.workDetails
                .getContext(),
                DividerItemDecoration.VERTICAL));
        adapter.bind(list);

        firebaseDatabase.getReference().child("WorkReport")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            WorkReport workReport = dataSnapshot.getValue(WorkReport.class);
                            list.add(workReport);
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
        ArrayList<WorkReport> filteredList = new ArrayList<>();
        for (WorkReport bin : list) {
            if (bin.getTime().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(bin);
            }
        }
        adapter.filteredList(filteredList);
    }

}