package com.proyecto.tvseriesapp.model;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.adapter.EpisodeListAdapter;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.view.EpisodesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Episode implements ITvSeriesHome.EpisodeModel {

    private ITvSeriesHome.EpisodePresenter iEpisodePresenter;
    private String name;
    List<Episode> episodeList;

    public Episode()
    {

    }
    public Episode(String name) {
        this.name = name;
    }
    public Episode(ITvSeriesHome.EpisodePresenter episodePresenter)
    {
        this.iEpisodePresenter = episodePresenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getEpisodes(final RecyclerView recyclerView, int seasonId) {
        episodeList = new ArrayList<>();
        EpisodesActivity episodesActivity = new EpisodesActivity();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(episodesActivity);
        final EpisodeListAdapter seriesListAdapter = new EpisodeListAdapter(episodeList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);

        Call<JsonArray> call1 = tvApi.getEpisodesByID("seasons/"+seasonId+"/episodes");
        call1.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        episodeList.clear();
                        for (int i = 0; i < response.body().size(); i++) {

                            JsonObject jsonObject = response.body().get(i).getAsJsonObject();
                            Episode episode = new Episode(jsonObject.get("name").getAsString());
                            Log.e("episodio" , episode.getName() +"");
                            episodeList.add(i,episode);
                        }
                        recyclerView.setAdapter(seriesListAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }
}
