package com.proyecto.tvseriesapp.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITvSeriesHome {
    interface  ViewHome
    {
        void showInformacion(String string);
    }
    interface PresenterHome
    {
        void showInformacion(String string);
        void getSeries(RecyclerView recyclerView,String id);
        void setUpRecyclerViewSeries(RecyclerView recyclerView);
    }
    interface ModelHome
    {
        void getSeries(RecyclerView recyclerView,String id);
        void setUpRecyclerViewSeries(RecyclerView recyclerView);

    }

}
