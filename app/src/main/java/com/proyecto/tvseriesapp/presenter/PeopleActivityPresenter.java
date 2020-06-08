package com.proyecto.tvseriesapp.presenter;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Person;

public class PeopleActivityPresenter implements ITvSeriesHome.PeoplePresenter {
    private ITvSeriesHome.PeopleView peopleView;
    private ITvSeriesHome.PeopleModel peopleModel;

    public PeopleActivityPresenter(ITvSeriesHome.PeopleView view) {
        this.peopleView = view;
        peopleModel = new Person(this);
    }

    @Override
    public void findPeople(RecyclerView recyclerView, String name) {

        if (peopleView != null) {
            peopleModel.findPeople(recyclerView, name);
        }
    }
}
