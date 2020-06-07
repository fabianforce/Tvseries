package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.model.Episode;
import com.proyecto.tvseriesapp.presenter.EpisodeActivityPresenter;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EpisodesActivity extends AppCompatActivity implements ITvSeriesHome.EpisodeView {

    private ITvSeriesHome.EpisodePresenter iPresenter;
    TextView seasontextView;
    RecyclerView seasonRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);
        seasontextView = findViewById(R.id.sid_txt);
        seasonRecycler = findViewById(R.id.episode_recycler);
        iPresenter = new EpisodeActivityPresenter(this);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.title_episode));
        Intent intent = getIntent();
        int id = intent.getIntExtra("seasonId", 0);
        String seasonName = intent.getStringExtra("seasonName");
        seasontextView.setText(seasonName);
        iPresenter.getEpisodes(seasonRecycler, id);


    }

    @Override
    public void showEpisodeDetail(Episode episode) {
        Log.e("COLOMBIA", episode.getName());
        Log.e("COLOMBIA", episode.getSummary());
        final Dialog dialog = new Dialog(EpisodesActivity.this);
        dialog.setContentView(R.layout.cutom_view);
        TextView nameTxt = dialog.findViewById(R.id.names_txt);
        TextView summaryTxt = dialog.findViewById(R.id.summarys_txt);
        Button dialogButton = dialog.findViewById(R.id.buttonOk);
        ImageView imageView = dialog.findViewById(R.id.serie_img);
        nameTxt.setText(episode.getName());
        summaryTxt.setText(episode.getSummary());
        Picasso.get().load(episode.getImage()).into(imageView);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
