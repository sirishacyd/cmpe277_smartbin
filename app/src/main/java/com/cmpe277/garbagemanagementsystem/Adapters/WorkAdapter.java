package com.cmpe277.garbagemanagementsystem.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cmpe277.garbagemanagementsystem.AccountsManagement.WorkReport;
import com.cmpe277.garbagemanagementsystem.R;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {
    ArrayList<WorkReport> list = new ArrayList<>();
    Context context;
    int count = 2;

    public WorkAdapter(Context context) {
        this.context = context;
    }

    public void bind(ArrayList<WorkReport> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void filteredList(ArrayList<WorkReport> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkAdapter.WorkViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.work_report, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position) {
        WorkReport workReport = list.get(position);
        count = count + 1;

        holder.binId.setText(workReport.getBinID());
        holder.binAddress.setText(workReport.getAddress());
        holder.loadType.setText(workReport.getLoadType());
        holder.cycle.setText(workReport.getCycle());
        holder.driverEmail.setText(workReport.getDriverMail());
        holder.time.setText(workReport.getTime());
        holder.todayCleaning.setText(workReport.getTodayCleaning());
        if(count%2 == 0) {
            holder.linear.setBackgroundColor(Color.parseColor("#CAA85B"));
        }else {
            holder.linear.setBackgroundColor(Color.parseColor("#7E75CB"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder {
        private final TextView binId, binAddress, loadType,
                todayCleaning, cycle, driverEmail, time;
        private LinearLayout linear;

        public WorkViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binId = itemView.findViewById(R.id.binId);
            this.binAddress = itemView.findViewById(R.id.binAddress);
//            this.city = itemView.findViewById(R.id.city);
            this.todayCleaning = itemView.findViewById(R.id.todayCleaning);
            this.driverEmail = itemView.findViewById(R.id.driverMail);
            this.cycle = itemView.findViewById(R.id.cycle);
            this.loadType = itemView.findViewById(R.id.loadType);
            this.time = itemView.findViewById(R.id.time);
            this.linear = itemView.findViewById(R.id.container);
        }
    }
}
