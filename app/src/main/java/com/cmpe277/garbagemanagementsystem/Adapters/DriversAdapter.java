package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.Drivers;
import com.cmpe277.garbagemanagementsystem.AdminMenu.CreateBin;
import com.cmpe277.garbagemanagementsystem.LogInStuff.DriverRegistration;
import com.cmpe277.garbagemanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import life.sabujak.roundedbutton.RoundedButton;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.DriversViewHolder> {
    ArrayList<Drivers> arrayList;
    Context context;

    public DriversAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public DriversViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DriversViewHolder(LayoutInflater.from(context).inflate(R.layout.drivers_detail,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull DriversViewHolder holder, int position) {
        Drivers drivers = arrayList.get(position);
        holder.email.setText("Email:  "+drivers.getEmailAddress());
        holder.userName.setText("Username:  " + drivers.getUserName());
        holder.cnic.setText("CNIC:  "+ drivers.getCnic());
        holder.password.setText("Password:  "+drivers.getUserPassword());

        holder.change.setOnClickListener(v -> {
            Intent intent = new Intent(context, DriverRegistration.class);
            intent.putExtra("email",drivers.getEmailAddress());
            intent.putExtra("username",drivers.getUserName());
            intent.putExtra("cnic",drivers.getCnic());
            intent.putExtra("password",drivers.getUserPassword());
            context.startActivity(intent);
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query binQuery = ref.child("Drivers").child(drivers.getUserName());

                binQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot driversSnapShot: dataSnapshot.getChildren()) {
                            driversSnapShot.getRef().removeValue();
                        }
                        Toast.makeText(context,"Driver Deleted Successfuly!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void Bind(ArrayList<Drivers> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }
    public void filterList(ArrayList<Drivers> list)
    {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class DriversViewHolder extends RecyclerView.ViewHolder {
        private  final TextView email,userName,cnic,password;
        private  final RoundedButton change,remove;

        public DriversViewHolder(@NonNull View itemView) {
            super(itemView);
            this.email = itemView.findViewById(R.id.email);
            this.userName = itemView.findViewById(R.id.userName);
            this.cnic = itemView.findViewById(R.id.Cnic);
            this.password = itemView.findViewById(R.id.password);
            this.change = itemView.findViewById(R.id.change);
            this.remove = itemView.findViewById(R.id.remove);
        }
    }
}

