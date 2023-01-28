package com.example.savesabaq;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.myViewHandler> {

    List<Record> records;

    public myRecyclerViewAdapter(List<Record> currentRecords) {
        this.records = currentRecords;
    }

    public Record getLatestRow() {
        LocalDate max = LocalDate.of(1947, 8, 14);
        Record record = new Record();
        int result;
        for (Record r:this.records) {
            result = max.compareTo(convertToDate(r.getDate()));
            if (result < 0) {
                max = convertToDate(r.getDate());
                record = r;
            }
        }
        Log.d("record.getDate()", record.getDate());
        Log.d("record.getsabaq()", record.getSabaq().toString());
        Log.d("record.getsabqi()", record.getSabqi().toString());
        Log.d("record.getDmanzil()", record.getManzil().toString());
        return record;
    }

    public LocalDate convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
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
        Record record = getLatestRow();
        if (record.getSabaq() != 0 && record.getSabaq() == holder.record.getSabaq()) {
            holder.sabaqflag.setImageResource(R.drawable.ic_baseline_refresh_24);
        }
        if (record.getSabqi() != 0 && record.getSabqi() == holder.record.getSabqi()) {
            holder.sabqiflag.setImageResource(R.drawable.ic_baseline_refresh_24);
        }
        if (record.getManzil() != 0 && record.getManzil() == holder.record.getManzil()) {
            holder.manzilflag.setImageResource(R.drawable.ic_baseline_refresh_24);
        }
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class myViewHandler extends RecyclerView.ViewHolder {
        TextView sabaqCount, sabqiCount, manzilCount, date;
        Record record;
        ImageView sabaqflag, sabqiflag, manzilflag;
        public myViewHandler(@NonNull View itemView) {
            super(itemView);
            sabaqCount =itemView.findViewById(R.id.sabaqCount);
            sabqiCount =itemView.findViewById(R.id.sabqiCount);
            manzilCount =itemView.findViewById(R.id.manzilCount);
            date =itemView.findViewById(R.id.date);
            sabaqflag = itemView.findViewById(R.id.sabaqflag);
            sabqiflag = itemView.findViewById(R.id.sabqiflag);
            manzilflag = itemView.findViewById(R.id.manzilflag);
        }
    }
}
