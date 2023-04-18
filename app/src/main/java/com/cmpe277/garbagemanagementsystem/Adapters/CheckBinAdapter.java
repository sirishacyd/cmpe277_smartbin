package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.WorkReport;
import com.cmpe277.garbagemanagementsystem.OpenMap;
import com.cmpe277.garbagemanagementsystem.R;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import life.sabujak.roundedbutton.RoundedButton;

public class CheckBinAdapter extends RecyclerView.Adapter<CheckBinAdapter.CheckBinViewHolder> {

    ArrayList<DigitalBin> arrayList;
    Context context;
    FirebaseDatabase firebaseDatabase;
    private String statuss = "";

    public CheckBinAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CheckBinAdapter.CheckBinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckBinAdapter.CheckBinViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.check_bin_details,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBinAdapter.CheckBinViewHolder holder, int position) {
        DigitalBin digitalBin = arrayList.get(position);
        String arr[] = {"Pending", "Done"};

        holder.binID.setText("Bin ID: " + digitalBin.getBinID());
        holder.binAddress.setText("Bin Address:  " + digitalBin.getBinAddress());
        holder.city.setText("City:  " + digitalBin.getCityName());
        holder.driverEmail.setText("Driver Email:  " + digitalBin.getDriverEmail());
        holder.bestRoute.setText("Best Route:  " + digitalBin.getBestRoute());
        holder.load.setText("Load Type:  " + digitalBin.getLoadType());
        holder.cycle.setText("Cycle:  " + digitalBin.getCycle());

        holder.showMap.setOnClickListener(v -> {
            Intent intent = new Intent(context, OpenMap.class);
            intent.putExtra("best_route", digitalBin.getBestRoute());
            context.startActivity(intent);
        });

        Random random = new Random();
        int rand = random.nextInt(1000000);

        holder.bind(holder.status, arr);
        holder.updateStatus.setOnClickListener(v -> {
            String time = String.valueOf(System.currentTimeMillis());

            String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(time)));
            WorkReport workReport = new WorkReport(digitalBin.getBinID(),
                    digitalBin.getBinAddress(),
                    digitalBin.getDriverEmail(), digitalBin.getLoadType()
                    , digitalBin.getCycle(),mydate, "done");
            firebaseDatabase.getInstance().getReference()
                    .child("WorkReport").child(String.valueOf(rand)).setValue(workReport);
            holder.status.setEnabled(false);
            arr[0] = "Done";
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void Bind(ArrayList<DigitalBin> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<DigitalBin> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class CheckBinViewHolder extends RecyclerView.ViewHolder {
        private final TextView binID, binAddress, city, driverEmail, bestRoute, load, cycle;
        Spinner status;
        private final RoundedButton showMap, updateStatus;

        public CheckBinViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binID = itemView.findViewById(R.id.binID);
            this.binAddress = itemView.findViewById(R.id.binAddress);
            this.city = itemView.findViewById(R.id.city);
            this.driverEmail = itemView.findViewById(R.id.driverMail);
            this.bestRoute = itemView.findViewById(R.id.bestRoote);
            this.load = itemView.findViewById(R.id.load);
            this.cycle = itemView.findViewById(R.id.cycal);
            this.showMap = itemView.findViewById(R.id.showMap);
            this.updateStatus = itemView.findViewById(R.id.updateStatus);
            this.status = itemView.findViewById(R.id.status);
        }

        public void bind(Spinner spinner, String[] arr) {
            //Creating the ArrayAdapter instance having the cycle list
            ArrayAdapter status = new ArrayAdapter(context,
                    android.R.layout.simple_spinner_item, arr);
            status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spinner.setAdapter(status);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    statuss = arr[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Toast.makeText(context, "Nothing really happened!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
