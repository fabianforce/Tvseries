package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.presenter.PeopleActivityPresenter;

public class PeopleActivity extends AppCompatActivity implements ITvSeriesHome.PeopleView {

    RecyclerView personRecyclerview;
    EditText nameditText;
    ImageButton findButton;
    private ITvSeriesHome.PeoplePresenter ipeoplePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        personRecyclerview = findViewById(R.id.person_recyclerview);
        nameditText = findViewById(R.id.mSearchPeopleName);
        findButton = findViewById(R.id.find_person_btn);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.title_people_activity));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        ipeoplePresenter = new PeopleActivityPresenter(this);
        ipeoplePresenter.findPeople(personRecyclerview,"A");
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipeoplePresenter.findPeople(personRecyclerview,nameditText.getText().toString());
            }
        });
    }
}
