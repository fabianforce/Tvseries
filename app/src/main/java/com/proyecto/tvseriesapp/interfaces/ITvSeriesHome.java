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
    }
    interface ModelHome
    {
        void getSeries(RecyclerView recyclerView,String id);
        void SetUpRecyclerViewDetail(RecyclerView recyclerView,JsonArray genresArray);
        void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray);
        void getSeasons(RecyclerView recyclerView, int id);

    }

    interface DetailView
    {
        void showEpisodes(int seasonId,String name);
    }
    interface DetailPresenter
    {
        void showEpisodes(int seasonId,String name);
        void SetUpRecyclerViewDetail(RecyclerView recyclerView,JsonArray genresArray);
        void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray);
        void getSeasons(RecyclerView recyclerView, int id);
    }


    interface EpisodeView
    {

    }

    interface EpisodePresenter
    {
        void getEpisodes(RecyclerView recyclerView,int seasonId);
    }

    interface EpisodeModel
    {
        void getEpisodes(RecyclerView recyclerView,int seasonId);
    }

}
