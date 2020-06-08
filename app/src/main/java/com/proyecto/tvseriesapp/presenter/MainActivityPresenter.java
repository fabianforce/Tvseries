package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;

import retrofit2.Call;

public class MainActivityPresenter implements ITvSeriesHome.HomePresenter {

    private ITvSeriesHome.HomeView iView;
    private ITvSeriesHome.HomeModel iModel;

    public MainActivityPresenter(ITvSeriesHome.HomeView view) {
        this.iView = view;
        iModel = new Series(this);
    }

    @Override
    public void showSerieDetail(String string, Series serie) {
        if (iView != null) {
            iView.showSerieDetail(string, serie);
        }
    }

    @Override
    public void getSeries(RecyclerView recyclerView, int num) {
        if (iView != null) {
            iModel.getSeries(recyclerView, num);
        }
    }

    @Override
    public void getSeriesByName(RecyclerView recyclerView, String name) {
        if (iView != null) {
            iModel.getSeriesByName(recyclerView, name);
        }
    }

}
