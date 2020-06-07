package com.proyecto.tvseriesapp.interfaces;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.model.Series;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface INetwork {

    @GET("shows")
    Call<JsonArray> getSeries(@Query("page") int num);

    @GET("search/shows")
    Call<JsonArray> getSeriesByName(@Query("q") String name);


    @GET()
    Call<JsonArray> getSeasonByID(@Url String url);

    @GET()
    Call<JsonArray> getEpisodesByID(@Url String url);
}
