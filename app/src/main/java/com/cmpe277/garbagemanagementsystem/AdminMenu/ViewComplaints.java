package com.cmpe277.garbagemanagementsystem.AdminMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.Drivers;
import com.cmpe277.garbagemanagementsystem.Adapters.RegCompAdapter;
import com.cmpe277.garbagemanagementsystem.Adapters.ViewComplaintsAdapter;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityUpdateComplaitBinding;
import com.cmpe277.garbagemanagementsystem.databinding.ActivityViewComplaintsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewComplaints extends AppCompatActivity {
    ActivityViewComplaintsBinding binding;
    ArrayList<Complaints> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    ViewComplaintsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewComplaintsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            firebaseDatabase = FirebaseDatabase.getInstance();

        //Search in Recyclerview
        binding.searchComplaints.addTextChangedListener(new TextWatcher() {
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


            adapter = new ViewComplaintsAdapter(this);
            binding.viewComplaints.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.viewComplaints.setLayoutManager(linearLayoutManager);
            adapter.Bind(list);
            binding.viewComplaints.addItemDecoration(
                    new DividerItemDecoration(binding.viewComplaints.getContext(),
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

    public void filter(String text) {
        ArrayList<Complaints> filteredList = new ArrayList<>();
        for (Complaints complaints : list) {
            if (complaints.getBinID().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(complaints);
            }
        }
        adapter.filterList(filteredList);
    }
}