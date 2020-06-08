package com.proyecto.tvseriesapp;

import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.presenter.PeopleActivityPresenter;
import com.proyecto.tvseriesapp.view.PeopleActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PeoplePresenterTest {

    private PeopleActivityPresenter peopleActivityPresenter;
    private String name = "Name";

    @Mock
    private PeopleActivity peopleActivity;


    @Before
    public void setUpPeoplePresenter() {
        MockitoAnnotations.initMocks(this);
        peopleActivityPresenter = new PeopleActivityPresenter(peopleActivity);
    }

    @Test
    public void findPeople(RecyclerView recyclerView, String name) {
        peopleActivityPresenter.findPeople(recyclerView,this.name);
        verify(peopleActivityPresenter).findPeople(recyclerView,this.name);
    }
}
