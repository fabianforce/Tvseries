package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.presenter.MainActivityPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements ITvSeriesHome.ViewHome {

    private ITvSeriesHome.PresenterHome iPresenter;
    RecyclerView recyclerViewSeries;
    Button findByName;
    EditText searchEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresenter = new MainActivityPresenter(this);
        recyclerViewSeries = findViewById(R.id.recyclerSerires);
        iPresenter.getSeries(recyclerViewSeries,"girl");
        findByName = findViewById(R.id.clickfind);
        searchEditText = findViewById(R.id.mSearchName);

        findByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.getSeries(recyclerViewSeries,searchEditText.getText().toString());
            }
        });

    }

    @Override
    public void showInformacion(String string) {
        Toast.makeText(this , string , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SeriesDetailsActivity.class);
        intent.putExtra("name", string);
        startActivity(intent);
    }
}
