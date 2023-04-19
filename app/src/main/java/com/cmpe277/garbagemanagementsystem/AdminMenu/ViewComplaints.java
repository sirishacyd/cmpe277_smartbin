package com.cmpe277.garbagemanagementsystem.AdminMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
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
}