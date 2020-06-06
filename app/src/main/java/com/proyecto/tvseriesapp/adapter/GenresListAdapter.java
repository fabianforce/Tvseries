package com.proyecto.tvseriesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.R;

import java.util.List;

public class GenresListAdapter  extends RecyclerView.Adapter<GenresListAdapter.GenreHolder> {
    List<String> genreList;

    public GenresListAdapter(List<String> genreList)
    {
        this.genreList = genreList;
    }
    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_item,parent,false);
        return new GenreHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.genreTextView.setText(genreList.get(position));
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public class GenreHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView genreTextView;

        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            mview = itemView;
            genreTextView = mview.findViewById(R.id.genre_txt);
        }
    }
}
