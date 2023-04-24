package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AdminMenu.CreateBin;
import com.cmpe277.garbagemanagementsystem.OpenMap;
import com.cmpe277.garbagemanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import life.sabujak.roundedbutton.RoundedButton;

public class BinAdapter extends Adapter<BinAdapter.BinViewHolder> {
    ArrayList<DigitalBin> arrayList;
    Context context;
    FirebaseDatabase firebaseDatabase;

    public BinAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public BinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BinViewHolder(LayoutInflater.from(context).inflate(R.layout.bin_details,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull BinViewHolder holder, int position) {
        DigitalBin digitalBin = arrayList.get(position);

        holder.binID.setText("Bin ID: " + digitalBin.getBinID());
        holder.binAddress.setText("Bin Address:  " + digitalBin.getBinAddress());
        holder.city.setText("City:  " + digitalBin.getCityName());
        holder.driverEmail.setText("Driver Email:  " + digitalBin.getDriverEmail());
        holder.bestRoute.setText("Best Route:  " + digitalBin.getBestRoute());
        holder.load.setText("Load Type:  "+digitalBin.getLoadType());
        holder.cycle.setText("Cycle:  "+digitalBin.getCycle());
        holder.update.setOnClickListener(v -> {
            Intent intent = new Intent(context, CreateBin.class);
            intent.putExtra("bin_id",digitalBin.getBinID());
            intent.putExtra("bin_address",digitalBin.getBinAddress());
            intent.putExtra("city_name",digitalBin.getCityName());
            intent.putExtra("driver_email",digitalBin.getDriverEmail());
            intent.putExtra("best_route",digitalBin.getBestRoute());
            context.startActivity(intent);
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query binQuery = ref.child("Digital Bin").child(digitalBin.getBinID());

                binQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot binSnapShot: dataSnapshot.getChildren()) {
                            binSnapShot.getRef().removeValue();
                        }
                        Toast.makeText(context,"Bin Deleted Successfully!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                notifyDataSetChanged();
            }
        });

        holder.showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OpenMap.class);
                intent.putExtra("best_route",digitalBin.getBestRoute());
                context.startActivity(intent);
            }
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
    public void filterList(ArrayList<DigitalBin> list)
    {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class BinViewHolder extends RecyclerView.ViewHolder {
        private final TextView binID, binAddress, city, driverEmail, bestRoute,load,cycle;
        private final RoundedButton update,delete,showMap;

        public BinViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binID = itemView.findViewById(R.id.binID);
            this.binAddress = itemView.findViewById(R.id.binAddress);
            this.city = itemView.findViewById(R.id.city);
            this.driverEmail = itemView.findViewById(R.id.driverMail);
            this.bestRoute = itemView.findViewById(R.id.bestRoote);
            this.update = itemView.findViewById(R.id.update);
            this.load = itemView.findViewById(R.id.load);
            this.cycle = itemView.findViewById(R.id.cycal);
            this.delete = itemView.findViewById(R.id.delete);
            this.showMap = itemView.findViewById(R.id.showMap);
        }
    }
}
