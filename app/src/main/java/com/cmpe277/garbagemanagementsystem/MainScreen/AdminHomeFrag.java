package com.cmpe277.garbagemanagementsystem.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cmpe277.garbagemanagementsystem.AdminMenu.CreateBin;
import com.cmpe277.garbagemanagementsystem.AdminMenu.UpdateBin;
import com.cmpe277.garbagemanagementsystem.AdminMenu.ViewComplaints;
import com.cmpe277.garbagemanagementsystem.AdminMenu.ViewDrivers;
import com.cmpe277.garbagemanagementsystem.AdminMenu.WorkDetails;
import com.cmpe277.garbagemanagementsystem.LogInStuff.DriverRegistration;
import com.cmpe277.garbagemanagementsystem.databinding.FragmentAdminHomeBinding;


public class AdminHomeFrag extends Fragment {
    FragmentAdminHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAdminHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //navigate to bin creation
        binding.createBin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreateBin.class);
            startActivity(intent);
        });

        //navigate to bin details
        binding.updateBin.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UpdateBin.class);
            startActivity(intent);
        });

        //navigate to driver details
        binding.viewDriver.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ViewDrivers.class);
            startActivity(intent);
        });


        //home text view components onclick
        binding.createDriver.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DriverRegistration.class);
            startActivity(intent);
        });

        //binding view complaints button
        binding.viewComplaints.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ViewComplaints.class);
            startActivity(intent);
        });

        binding.workreport.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WorkDetails.class);
            startActivity(intent);
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}