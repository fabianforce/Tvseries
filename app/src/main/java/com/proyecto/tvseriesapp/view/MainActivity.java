package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Series;
import com.proyecto.tvseriesapp.presenter.MainActivityPresenter;

import java.io.Externalizable;
import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements ITvSeriesHome.HomeView {

    private ITvSeriesHome.HomePresenter iPresenter;
    RecyclerView recyclerViewSeries;
    Button lpaginationButton, rpaginationButton;
    ImageButton findByName;
    EditText searchEditText;
    int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenter = new MainActivityPresenter(this);
        recyclerViewSeries = findViewById(R.id.recyclerSerires);
        iPresenter.getSeries(recyclerViewSeries, pageNumber);
        findByName = findViewById(R.id.clickfind);
        searchEditText = findViewById(R.id.mSearchName);
        lpaginationButton = findViewById(R.id.lpagination_btn);
        rpaginationButton = findViewById(R.id.rpagination_btn);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));

        findByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.getSeriesByName(recyclerViewSeries, searchEditText.getText().toString());
            }
        });

        lpaginationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNumber -= 1;
                if (pageNumber == 0) {
                    pageNumber = 1;
                }
                iPresenter.getSeries(recyclerViewSeries, pageNumber);
                Log.e("MENOR", pageNumber + "");
            }
        });
        rpaginationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNumber += 1;
                Log.e("mas ", pageNumber + "");
                iPresenter.getSeries(recyclerViewSeries, pageNumber);
            }
        });

    }

    @Override
    public void showInformacion(String string, Series serie) {
        Intent intent = new Intent(this, SeriesDetailsActivity.class);
        Gson gS = new Gson();
        String target = gS.toJson(serie);
        intent.putExtra("serieDetail", target);
        startActivity(intent);
    }
}
