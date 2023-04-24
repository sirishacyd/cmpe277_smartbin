package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
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

import com.cmpe277.garbagemanagementsystem.AccountsManagement.Complaints;
import com.cmpe277.garbagemanagementsystem.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import life.sabujak.roundedbutton.RoundedButton;

public class ViewComplaintsAdapter extends RecyclerView.Adapter<ViewComplaintsAdapter.CompViewHolder> {

    ArrayList<Complaints> arrayList;
    Context context;
    FirebaseDatabase firebaseDatabase;
    private String statuss = "";

    public ViewComplaintsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewComplaintsAdapter.CompViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewComplaintsAdapter.CompViewHolder(LayoutInflater.from(context).inflate(R.layout.view_complaint,
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewComplaintsAdapter.CompViewHolder holder, int position) {
        Complaints complaints = arrayList.get(position);
        String arr[] = {"Pending", "Completed"};

        holder.binId.setText("Bin ID: " + complaints.getBinID());
        holder.title.setText("Title:  " + complaints.getTitle());
        holder.description.setText("Description:  " + complaints.getDescription());
        holder.bind(holder.status, arr);
        if (complaints.getStatus().equals("Pending")) {
            holder.status.setEnabled(true);
        } else {
            holder.status.setEnabled(false);
            arr[0] = "Completed";
        }
        holder.done.setOnClickListener(v -> {
            firebaseDatabase.getInstance().getReference().child("Complaints")
                    .child(complaints.getBinID()).child("status").setValue(statuss);
            notifyDataSetChanged();
            Toast.makeText(context, "Status updated!", Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void Bind(ArrayList<Complaints> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Complaints> list)
    {
        this.arrayList = list;
        notifyDataSetChanged();
    }

    public class CompViewHolder extends RecyclerView.ViewHolder {
        private final TextView binId, title, description;
        private final Spinner status;
        private final RoundedButton done;

        public CompViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binId = itemView.findViewById(R.id.binId);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.status = itemView.findViewById(R.id.status);
            this.done = itemView.findViewById(R.id.remove);
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
