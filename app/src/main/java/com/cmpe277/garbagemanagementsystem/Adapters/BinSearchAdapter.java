package com.cmpe277.garbagemanagementsystem.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.BinID;
import com.cmpe277.garbagemanagementsystem.R;
import com.cmpe277.garbagemanagementsystem.UserMenu.RegisterComplaint;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BinSearchAdapter extends RecyclerView.Adapter<BinSearchAdapter.CompViewHolder> {
    ArrayList<BinID> arrayList;
    Context context;
    FirebaseDatabase firebaseDatabase;

    public BinSearchAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public BinSearchAdapter.CompViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BinSearchAdapter.CompViewHolder(LayoutInflater.from(context).inflate(R.layout.bin_ids,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull BinSearchAdapter.CompViewHolder holder, int position) {
        BinID binID = arrayList.get(position);
        holder.binID.setText("Bin ID: " + binID.getBinID());
        holder.binID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterComplaint.class);
                intent.putExtra("bin_id",binID.getBinID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void Bind(ArrayList<BinID> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }
    public void filterList(ArrayList<BinID> list)
    {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class CompViewHolder extends RecyclerView.ViewHolder {
        private final TextView binID;

        public CompViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binID = itemView.findViewById(R.id.compBinID);
        }
    }
}