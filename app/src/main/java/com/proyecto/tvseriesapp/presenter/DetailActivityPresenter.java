package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;

public class DetailActivityPresenter implements ITvSeriesHome.DetailPresenter {

    private ITvSeriesHome.DetailView iView;
    private ITvSeriesHome.HomeModel iModel;

    public DetailActivityPresenter(ITvSeriesHome.DetailView view) {
        this.iView = view;
        iModel = new Series(this);
    }

    @Override
    public void showEpisodes(int seasonId, String name) {
        if (iView != null) {
            iView.showEpisodes(seasonId, name);
        }
    }

    @Override
    public void SetUpRecyclerViewDetail(RecyclerView recyclerView, JsonArray genresArray) {
        if (iView != null) {
            iModel.SetUpRecyclerViewDetail(recyclerView, genresArray);
        }
    }

    @Override
    public void SetUpRecyclerDays(RecyclerView recyclerView, JsonArray daysArray) {
        if (iView != null) {
            iModel.SetUpRecyclerDays(recyclerView, daysArray);
        }
    }

    @Override
    public void getSeasons(RecyclerView recyclerView, int id) {
        if (iView != null) {
            iModel.getSeasons(recyclerView, id);
        }
    }
}
