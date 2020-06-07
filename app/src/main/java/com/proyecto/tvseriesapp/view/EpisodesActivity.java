package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.presenter.EpisodeActivityPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EpisodesActivity extends AppCompatActivity implements ITvSeriesHome.EpisodeView {

    private ITvSeriesHome.EpisodePresenter iPresenter;
    TextView seasontextView;
    RecyclerView seasonRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);
        seasontextView = findViewById(R.id.sid_txt);
        seasonRecycler = findViewById(R.id.episode_recycler);
        iPresenter = new EpisodeActivityPresenter(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("seasonId",0);
        String seasonName = intent.getStringExtra("seasonName");
        seasontextView.setText(seasonName);
        iPresenter.getEpisodes(seasonRecycler,id);
    }
}
