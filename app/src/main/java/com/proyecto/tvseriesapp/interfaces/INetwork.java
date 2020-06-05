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

public interface INetwork {

    @FormUrlEncoded
    @POST("search/shows")
    Call<JsonArray> getSeriesId(@Field("q") String q);

    @GET("search/shows")
    Call<JsonArray> getSeries(@Query("q") String q);

}
