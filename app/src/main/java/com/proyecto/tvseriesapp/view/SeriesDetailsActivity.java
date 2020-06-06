package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.adapter.GenresListAdapter;
import com.proyecto.tvseriesapp.adapter.SeriesListAdapter;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;
import com.proyecto.tvseriesapp.presenter.DetailActivityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SeriesDetailsActivity extends AppCompatActivity implements ITvSeriesHome.DetailView {
    private ITvSeriesHome.DetailPresenter iPresenter;
    TextView textViewName,textViewScore,textViewSummary;
    ImageView imageView;
    RecyclerView genreRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        iPresenter = new DetailActivityPresenter(this);
        textViewScore = findViewById(R.id.score_text);
        textViewName = findViewById(R.id.name_text);
        imageView = findViewById(R.id.img_detail_de);
        textViewSummary = findViewById(R.id.summary_text);
        genreRecyclerView = findViewById(R.id.genre_recycler);

        Intent intent = getIntent();
        Gson gS = new Gson();
        Series serie = gS.fromJson(intent.getStringExtra("serieDetail"), Series.class);
        Picasso.get().load(serie.getImage()).into(imageView);
        textViewName.setText(serie.getName());
        textViewScore.setText(serie.getScore());
        textViewSummary.setText(serie.getSummary());

        iPresenter.SetUpRecyclerViewDetail(genreRecyclerView,serie.getGenres());
    }

    @Override
    public void showInformacion(String string, Series serie) {

    }
}
