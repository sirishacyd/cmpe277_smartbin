package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.AccountsManagement.DigitalBin;
import com.cmpe277.garbagemanagementsystem.AdminMenu.CreateBin;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.UserMenu.RegisterComplaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import life.sabujak.roundedbutton.RoundedButton;

public class RegCompAdapter extends RecyclerView.Adapter<RegCompAdapter.RegCompViewHolder> {
    ArrayList<Complaints> arrayList;
    Context context;
    FirebaseDatabase firebaseDatabase;

    public RegCompAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RegCompAdapter.RegCompViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegCompAdapter.RegCompViewHolder(LayoutInflater.from(context).inflate(R.layout.view_complaints,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegCompAdapter.RegCompViewHolder holder, int position) {
        Complaints complaints = arrayList.get(position);

        holder.binId.setText("Bin ID: " + complaints.getBinID());
        holder.title.setText("Title:  " + complaints.getTitle());
        holder.description.setText("Description:  " + complaints.getDescription());
        holder.status.setText("Status:  " + complaints.getStatus());

        holder.change.setOnClickListener(v -> {
            Intent intent = new Intent(context, RegisterComplaint.class);
            intent.putExtra("bin_id",complaints.getBinID());
            intent.putExtra("title",complaints.getTitle());
            intent.putExtra("description",complaints.getDescription());
            context.startActivity(intent);

        });
        holder.delete.setOnClickListener(v -> {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query binQuery = ref.child("Complaints").child(complaints.getBinID());

            binQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot complaintSnapShot: dataSnapshot.getChildren()) {
                        complaintSnapShot.getRef().removeValue();
                    }
                    Toast.makeText(context,"Complaint Deleted!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            notifyDataSetChanged();
        });





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void Bind(ArrayList<Complaints> list){
        this.arrayList = list;
    }

    public class RegCompViewHolder extends RecyclerView.ViewHolder {
        private final TextView binId, title, description, status;
        RoundedButton change, delete;

        public RegCompViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binId = itemView.findViewById(R.id.binId);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.status = itemView.findViewById(R.id.status);
            this.change = itemView.findViewById(R.id.compChange);
            this.delete = itemView.findViewById(R.id.remove);
        }
    }
}
