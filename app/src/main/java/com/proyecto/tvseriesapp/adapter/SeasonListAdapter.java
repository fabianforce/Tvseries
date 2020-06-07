package com.proyecto.tvseriesapp.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.R;

import org.json.JSONObject;

import java.util.List;

public class SeasonListAdapter extends RecyclerView.Adapter<SeasonListAdapter.SeasonHolder> {
    List<JsonObject> seasonList;
    private onItemClickSeasonListener seasonListener;

    public SeasonListAdapter(List<JsonObject> seasonList) {
        this.seasonList = seasonList;
    }

    @NonNull
    @Override
    public SeasonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_item, parent, false);
        return new SeasonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonHolder holder, final int position) {
        holder.seasonTextView.setText("Season " + seasonList.get(position).get("number").getAsString());
        holder.seasonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seasonListener.showEpisodes(seasonList.get(position).get("id").getAsInt(), "Season " + seasonList.get(position).get("number").getAsString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return seasonList.size();
    }

    public class SeasonHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView seasonTextView;

        public SeasonHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            seasonTextView = mview.findViewById(R.id.season_name_txt);
        }
    }

    public interface onItemClickSeasonListener {
        void showEpisodes(int seasonId, String name);
    }

    public void setOnItemClickListener(onItemClickSeasonListener onItemClickListener) {
        this.seasonListener = onItemClickListener;
    }
}
