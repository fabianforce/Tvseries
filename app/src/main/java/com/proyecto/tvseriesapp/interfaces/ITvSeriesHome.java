package com.proyecto.tvseriesapp.interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.model.Episode;
import com.proyecto.tvseriesapp.model.Series;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITvSeriesHome {
    interface HomeView {
        void showInformacion(String string, Series serie);
    }

    interface HomePresenter {
        void showInformacion(String string, Series serie);

        void getSeries(RecyclerView recyclerView, int num);

        void getSeriesByName(RecyclerView recyclerView, String name);
    }

    interface HomeModel {
        void getSeries(RecyclerView recyclerView, int num);

        void SetUpRecyclerViewDetail(RecyclerView recyclerView, JsonArray genresArray);

        void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray);

        void getSeasons(RecyclerView recyclerView, int id);

        void getSeriesByName(RecyclerView recyclerView, String name);

    }

    interface DetailView {
        void showEpisodes(int seasonId, String name);
    }

    interface DetailPresenter {
        void showEpisodes(int seasonId, String name);

        void SetUpRecyclerViewDetail(RecyclerView recyclerView, JsonArray genresArray);

        void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray);

        void getSeasons(RecyclerView recyclerView, int id);
    }


    interface EpisodeView {
        void showEpisodeDetail(Episode episode);
    }

    interface EpisodePresenter {
        void showEpisodeDetail(Episode episode);

        void getEpisodes(RecyclerView recyclerView, int seasonId);
    }

    interface EpisodeModel {
        void getEpisodes(RecyclerView recyclerView, int seasonId);
    }

}
