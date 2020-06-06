package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;

public class DetailActivityPresenter implements ITvSeriesHome.DetailPresenter {

    private ITvSeriesHome.DetailView iView;
    private ITvSeriesHome.ModelHome iModel;

    public DetailActivityPresenter(ITvSeriesHome.DetailView view)
    {
        this.iView = view;
        iModel = new Series(this);
    }

    @Override
    public void showInformacion(String string, Series serie) {
        if(iView!=null)
        {
            iView.showInformacion(string,serie);
        }
    }

    @Override
    public void SetUpRecyclerViewDetail(RecyclerView recyclerView, JsonArray genresArray) {
        if(iView!=null)
        {
            iModel.SetUpRecyclerViewDetail(recyclerView,genresArray);
        }
    }
}
