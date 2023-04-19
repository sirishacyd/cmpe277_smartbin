package com.cmpe277.garbagemanagementsystem.DriverMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.cmpe277.garbagemanagementsystem.OpenMap;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.databinding.FragmentDriverHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DriverHomeFrag extends Fragment {
    FragmentDriverHomeBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        binding = FragmentDriverHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // driver searching for bin
        binding.digitalBins.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CheckBin.class);
            startActivity(intent);
        });

        //show the map
        binding.map.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OpenMap.class);
            startActivity(intent);
        });

        binding.myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DriverProfile.class);
                startActivity(intent);
            }
        });



        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}