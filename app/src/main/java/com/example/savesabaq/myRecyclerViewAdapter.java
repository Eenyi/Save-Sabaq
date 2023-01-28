package com.example.savesabaq;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.myViewHandler> {

    List<Record> records;

    public myRecyclerViewAdapter(List<Record> currentRecords) {
        this.records = currentRecords;
    }

    @NonNull
    @Override
    public myViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_record, parent, false);
        return new myViewHandler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHandler holder, int position) {
        holder.record = records.get(position);
        holder.date.setText(holder.record.getDate());
        holder.sabaqCount.setText(holder.record.getSabaq().toString());
        holder.sabqiCount.setText(holder.record.getSabqi().toString());
        holder.manzilCount.setText(holder.record.getManzil().toString());
    }

    @Override
    public int getItemCount() {
        Log.d("returned records", String.valueOf( records.size()));
        return records.size();
    }

    public class myViewHandler extends RecyclerView.ViewHolder {
        TextView sabaqCount, sabqiCount, manzilCount, date;
        Record record;
        public myViewHandler(@NonNull View itemView) {
            super(itemView);
            sabaqCount =itemView.findViewById(R.id.sabaqCount);
            sabqiCount =itemView.findViewById(R.id.sabqiCount);
            manzilCount =itemView.findViewById(R.id.manzilCount);
            date =itemView.findViewById(R.id.date);
        }
    }
}
