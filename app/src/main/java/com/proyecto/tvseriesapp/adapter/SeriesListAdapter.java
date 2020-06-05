package com.proyecto.tvseriesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.model.Series;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesListAdapter.SeriresHolder> {

    List<Series> seriesObj;
    private onItemClickDetailListener detailListener;

    public SeriesListAdapter(List<Series> seriesList)
    {
        this.seriesObj = seriesList;
    }

    @NonNull
    @Override
    public SeriresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serie_item,parent,false);
        return new SeriresHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriresHolder seriresHolder, final int position) {

        seriresHolder.txtScore.setText(seriesObj.get(position).getName());
        seriresHolder.showSerieImage(seriesObj.get(position).getImage());
        seriresHolder.txtScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailListener.showDetails(seriesObj.get(position).getName(),seriesObj.get(position).getImage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesObj.size();
    }

    public class SeriresHolder extends RecyclerView.ViewHolder {
        TextView txtScore;
        ImageView serieImage;
        Button btnCartMore, btnCartLess;
        View mview;

        public SeriresHolder(@NonNull final View itemView) {
            super(itemView);
            mview = itemView;
            txtScore = mview.findViewById(R.id.score_txt);
            serieImage = mview.findViewById(R.id.img_serie);
        }
        public void showSerieImage(String url)
        {
            Picasso.get().load(url).into(serieImage);
        }

    }

    public interface onItemClickDetailListener {
        void showDetails(String name, String image);
    }

    public void setOnItemClickListener(onItemClickDetailListener onItemClickListener) {
        this.detailListener = onItemClickListener;
    }
}
