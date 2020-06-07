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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.adapter.GenresListAdapter;
import com.proyecto.tvseriesapp.adapter.SeriesListAdapter;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;
import com.proyecto.tvseriesapp.presenter.DetailActivityPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SeriesDetailsActivity extends AppCompatActivity implements ITvSeriesHome.DetailView {
    private ITvSeriesHome.DetailPresenter iPresenter;
    TextView textViewName,textViewScore,textViewSummary,textViewTime;
    ImageView imageView;
    RecyclerView genreRecyclerView,dayRecyclerView,seasonRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        iPresenter = new DetailActivityPresenter(this);
        textViewScore = findViewById(R.id.score_text);
        textViewName = findViewById(R.id.name_text);
        imageView = findViewById(R.id.img_detail_de);
        textViewSummary = findViewById(R.id.summary_text);
        textViewTime = findViewById(R.id.time_text);
        genreRecyclerView = findViewById(R.id.genre_recycler);
        dayRecyclerView = findViewById(R.id.day_recycler);
        seasonRecyclerView = findViewById(R.id.season_recycler);

        Intent intent = getIntent();
        Gson gS = new Gson();
        Series serie = gS.fromJson(intent.getStringExtra("serieDetail"), Series.class);
        Picasso.get().load(serie.getImage()).into(imageView);
        textViewName.setText(serie.getName());
        textViewScore.setText(serie.getScore());
        textViewSummary.setText(serie.getSummary());
        textViewTime.setText(serie.getSchedule().get("time").getAsString());

        iPresenter.SetUpRecyclerViewDetail(genreRecyclerView,serie.getGenres());
        iPresenter.SetUpRecyclerDays(dayRecyclerView,serie.getSchedule().get("days").getAsJsonArray());
        iPresenter.getSeasons(seasonRecyclerView , serie.getId());

        //Log.e("ID SERIE" , serie.getId()+"");

    }

    @Override
    public void showEpisodes(int seasonId,String name) {
        Intent intent = new Intent(this, EpisodesActivity.class);
        Log.e("DIOS MIO" , name);
        intent.putExtra("seasonId" , seasonId);
        intent.putExtra("seasonName" , name);
        startActivity(intent);
    }
}
