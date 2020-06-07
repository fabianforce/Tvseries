package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;

import retrofit2.Call;

public class MainActivityPresenter implements ITvSeriesHome.PresenterHome {

    private ITvSeriesHome.ViewHome iView;
    private ITvSeriesHome.ModelHome iModel;

    public MainActivityPresenter(ITvSeriesHome.ViewHome view)
    {
        this.iView = view;
        iModel = new Series(this);
    }

    @Override
    public void showInformacion(String string,Series serie) {
        if(iView!=null)
        {
            iView.showInformacion(string,serie);
        }
    }

    @Override
    public void getSeries(RecyclerView recyclerView,String id) {
        if(iView!=null)
        {
            iModel.getSeries(recyclerView,id);
        }
    }

}
