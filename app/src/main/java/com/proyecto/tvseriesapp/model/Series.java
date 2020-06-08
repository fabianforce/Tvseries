package com.proyecto.tvseriesapp.model;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.adapter.DayListAdapter;
import com.proyecto.tvseriesapp.adapter.GenresListAdapter;
import com.proyecto.tvseriesapp.adapter.SeasonListAdapter;
import com.proyecto.tvseriesapp.adapter.SeriesListAdapter;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.view.MainActivity;
import com.proyecto.tvseriesapp.view.SeriesDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Series implements ITvSeriesHome.HomeModel {
    private ITvSeriesHome.HomePresenter iPresenter;
    private ITvSeriesHome.DetailPresenter iDetailPresenter;
    private int id;
    private String score;
    private String name;
    private String image;
    private String summary;
    private JsonArray genres;
    private JsonObject schedule;
    SeriesListAdapter seriesListAdapter;

    List<Series> seriesList;
    List<String> genreList;
    List<String> daysList;
    List<JsonObject> season;
    String url = "";
    String summaryText = "";

    public Series() {

    }

    public Series(int id, String score, String name, String image, String summary, JsonArray genres, JsonObject schedule) {
        this.id = id;
        this.score = score;
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.genres = genres;
        this.schedule = schedule;
    }

    public Series(ITvSeriesHome.HomePresenter presenterHome) {
        this.iPresenter = presenterHome;
    }

    public Series(ITvSeriesHome.DetailPresenter detailPresenter) {
        this.iDetailPresenter = detailPresenter;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public JsonArray getGenres() {
        return genres;
    }

    public JsonObject getSchedule() {
        return schedule;
    }

    public void setSchedule(JsonObject schedule) {
        this.schedule = schedule;
    }

    public void setGenres(JsonArray genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void getSeries(final RecyclerView recyclerView, int id) {
        seriesList = new ArrayList<>();
        MainActivity mainActivity = new MainActivity();
        //setUp Series Recyclerview
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
        seriesListAdapter = new SeriesListAdapter(seriesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        //call get retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);
        Call<JsonArray> call1 = tvApi.getSeries(id);
        call1.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        seriesList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            JsonObject jsonObject = response.body().get(i).getAsJsonObject();
                            /** check if the image exist**/
                            if (jsonObject.get("image").isJsonNull()) {
                                url = "https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101028/112815904-stock-vector-no-image-available-icon-flat-vector-illustration.jpg?ver=6";
                            } else if (jsonObject.get("summary").isJsonNull()) {
                                summaryText = "------";
                            } else {
                                url = jsonObject.getAsJsonObject("image").get("medium").getAsString();
                                summaryText = jsonObject.get("summary").toString();
                            }
                            Series serie = new Series(jsonObject.get("id").getAsInt(), jsonObject.get("language").getAsString(), jsonObject.get("name").getAsString(), url, summaryText, jsonObject.get("genres").getAsJsonArray(), jsonObject.getAsJsonObject("schedule"));
                            seriesList.add(i, serie);
                        }
                        recyclerView.setAdapter(seriesListAdapter);
                        seriesListAdapter.setOnItemClickListener(new SeriesListAdapter.onItemClickDetailListener() {
                            @Override
                            public void showDetails(String name, Series serie) {
                                iPresenter.showSerieDetail(name, serie);
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("response fail", t.getMessage() + "");
            }
        });
    }

    @Override
    public void SetUpRecyclerViewDetail(RecyclerView recyclerView, JsonArray genresArray) {
        genreList = new ArrayList<>();
        SeriesDetailsActivity seriesDetailsActivity = new SeriesDetailsActivity();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(seriesDetailsActivity, LinearLayoutManager.HORIZONTAL, false);
        final GenresListAdapter genreListAdapter = new GenresListAdapter(genreList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        for (JsonElement e : genresArray) {
            genreList.add(e.getAsString());
        }
        recyclerView.setAdapter(genreListAdapter);
    }

    @Override
    public void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray) {
        daysList = new ArrayList<>();
        SeriesDetailsActivity seriesDetailsActivity = new SeriesDetailsActivity();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(seriesDetailsActivity, LinearLayoutManager.HORIZONTAL, false);
        final DayListAdapter dayListAdapter = new DayListAdapter(daysList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        for (JsonElement e : daysArray) {
            daysList.add(e.getAsString());
        }
        recyclerView.setAdapter(dayListAdapter);

    }

    @Override
    public void getSeasons(final RecyclerView recyclerView, int id) {

        season = new ArrayList<>();
        SeriesDetailsActivity seriesDetailsActivity = new SeriesDetailsActivity();
        //setUp Series Recyclerview
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(seriesDetailsActivity, LinearLayoutManager.HORIZONTAL, false);
        final SeasonListAdapter seasonListAdapter = new SeasonListAdapter(season);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);

        Call<JsonArray> call1 = tvApi.getSeasonByID("shows/" + id + "/seasons");
        call1.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        season.clear();
                        for (int i = 0; i < response.body().size(); i++) {

                            JsonObject jsonObject = response.body().get(i).getAsJsonObject();
                            season.add(i, jsonObject);
                        }

                        recyclerView.setAdapter(seasonListAdapter);
                        seasonListAdapter.setOnItemClickListener(new SeasonListAdapter.onItemClickSeasonListener() {
                            @Override
                            public void showEpisodes(int seasonId, String name) {
                                iDetailPresenter.showEpisodes(seasonId, name);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });
    }

    @Override
    public void getSeriesByName(final RecyclerView recyclerView,String name) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);
        Call<JsonArray> call1 = tvApi.getSeriesByName(name);
        call1.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        seriesList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            JsonObject jsonObject = response.body().get(i).getAsJsonObject();
                            Log.e("DIOS MIO" , jsonObject +"");
                            /** check if the image exist**/
                            if (jsonObject.get("show").getAsJsonObject().get("image").isJsonNull()) {
                                url = "https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101028/112815904-stock-vector-no-image-available-icon-flat-vector-illustration.jpg?ver=6";
                            } else if (jsonObject.get("show").getAsJsonObject().get("summary").isJsonNull()) {
                                summaryText = "------";
                            } else {
                                url = jsonObject.get("show").getAsJsonObject().getAsJsonObject("image").get("medium").getAsString();
                                summaryText = jsonObject.get("show").getAsJsonObject().get("summary").getAsString();
                            }
                            Series serie = new Series(jsonObject.get("show").getAsJsonObject().get("id").getAsInt(), "", jsonObject.get("show").getAsJsonObject().get("name").getAsString(), url, summaryText, jsonObject.get("show").getAsJsonObject().get("genres").getAsJsonArray(), jsonObject.get("show").getAsJsonObject().getAsJsonObject("schedule"));
                            seriesList.add(i, serie);
                        }
                        recyclerView.setAdapter(seriesListAdapter);
                        seriesListAdapter.setOnItemClickListener(new SeriesListAdapter.onItemClickDetailListener() {
                            @Override
                            public void showDetails(String name, Series serie) {
                                iPresenter.showSerieDetail(name,serie);
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("response fail",t.getMessage()+"");
            }
        });
    }


}
