package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Episode;

public class EpisodeActivityPresenter implements ITvSeriesHome.EpisodePresenter {
    private ITvSeriesHome.EpisodeView iView;
    private ITvSeriesHome.EpisodeModel iModel;

    public EpisodeActivityPresenter(ITvSeriesHome.EpisodeView view) {
        this.iView = view;
        iModel = new Episode(this);
    }

    @Override
    public void showEpisodeDetail(Episode episode) {
        if (iView != null) {
            iView.showEpisodeDetail(episode);
        }
    }

    @Override
    public void getEpisodes(RecyclerView recyclerView, int seasonId) {
        if (iView != null) {
            iModel.getEpisodes(recyclerView, seasonId);
        }
    }
}
