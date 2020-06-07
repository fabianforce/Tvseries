package com.proyecto.tvseriesapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.model.Episode;

import java.util.List;

public class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.EpisodeHolder> {
    List<Episode> episodeList;
    private onItemClickEpisodeListener episodeListener;

    public EpisodeListAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false);
        return new EpisodeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, final int position) {
        holder.episodeTextView.setText(episodeList.get(position).getName());
        holder.episodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                episodeListener.showEpisodeInfo(episodeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public class EpisodeHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView episodeTextView;

        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            episodeTextView = mview.findViewById(R.id.episode_txt);
        }
    }

    public interface onItemClickEpisodeListener {
        void showEpisodeInfo(Episode episode);
    }

    public void setOnItemClickListener(onItemClickEpisodeListener onItemClickListener) {
        this.episodeListener = onItemClickListener;
    }
}
