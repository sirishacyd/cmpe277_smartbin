package com.cmpe277.garbagemanagementsystem.MainScreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.UserMenu.RegisterComplaint;
import com.cmpe277.garbagemanagementsystem.UserMenu.UpdateComplaint;
import com.cmpe277.garbagemanagementsystem.UserMenu.UserProfile;
import com.cmpe277.garbagemanagementsystem.databinding.FragmentUserHomeBinding;

public class UserHomeFrag extends Fragment {
    FragmentUserHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        binding = FragmentUserHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //binding register complaint
        binding.registerComplain.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RegisterComplaint.class);
            startActivity(intent);
        });

        //binding view complaints buttons
        binding.myComplains.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UpdateComplaint.class);
            startActivity(intent);
        });

        binding.myProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserProfile.class);
            startActivity(intent);
        });


        // Inflate the layout for this fragment
        return binding.getRoot();


    }
}