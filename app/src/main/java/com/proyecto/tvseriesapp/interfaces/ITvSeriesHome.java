package com.proyecto.tvseriesapp.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.model.Series;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITvSeriesHome {
    interface  ViewHome
    {
        void showInformacion(String string,Series serie);
    }
    interface PresenterHome
    {
        void showInformacion(String string, Series serie);
        void getSeries(RecyclerView recyclerView,String id);
        void setUpRecyclerViewSeries(RecyclerView recyclerView);
    }
    interface ModelHome
    {
        void getSeries(RecyclerView recyclerView,String id);
        void setUpRecyclerViewSeries(RecyclerView recyclerView);
        void SetUpRecyclerViewDetail(RecyclerView recyclerView,JsonArray genresArray);

    }

    interface DetailView
    {
        void showInformacion(String string,Series serie);
    }
    interface DetailPresenter
    {
        void showInformacion(String string,Series serie);
        void SetUpRecyclerViewDetail(RecyclerView recyclerView,JsonArray genresArray);
    }

}
