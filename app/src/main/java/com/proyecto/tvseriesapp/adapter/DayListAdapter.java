package com.proyecto.tvseriesapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.R;

import java.util.List;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.DayHolder> {
    List<String> dayList;

    public DayListAdapter(List<String> dayList) {
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
        return new DayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayHolder holder, int position) {
        holder.dayTextView.setText(dayList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public class DayHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView dayTextView;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            dayTextView = mview.findViewById(R.id.day_txt);
        }
    }
}
