package com.proyecto.tvseriesapp.model;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.proyecto.tvseriesapp.adapter.SeriesListAdapter;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Series implements ITvSeriesHome.ModelHome {
    private ITvSeriesHome.PresenterHome iPresenter;
    private String score;
    private String name;
    private String image;
    private SeriesListAdapter seriesListAdapter;
    List<Series> seriesList;
    String url = "";

    public Series()
    {

    }

    public Series(String score, String name, String image) {
        this.score = score;
        this.name = name;
        this.image = image;
    }

    public Series(ITvSeriesHome.PresenterHome presenterHome)
    {
        this.iPresenter = presenterHome;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void getSeries(final RecyclerView recyclerView, String id) {
        seriesList = new ArrayList<>();
        MainActivity mainActivity = new MainActivity();
        //setUp Series Recyclerview
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
        final SeriesListAdapter seriesListAdapter = new SeriesListAdapter(seriesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        //call get retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);
        Call<JsonArray> call1 = tvApi.getSeries(id);
        call1.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        seriesList.clear();
                        for(int i = 0 ;i<response.body().size();i++)
                        {
                            JsonObject jsonObject =response.body().get(i).getAsJsonObject();
                            /** check if the image exist**/
                            if(jsonObject.get("show").getAsJsonObject().get("image").isJsonNull())
                            {
                                url = "https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101028/112815904-stock-vector-no-image-available-icon-flat-vector-illustration.jpg?ver=6";
                            }else
                            {
                                url = jsonObject.get("show").getAsJsonObject().getAsJsonObject("image").get("medium").getAsString();
                            }
                            Log.e("Maria" , jsonObject.get("show").getAsJsonObject().get("image")+"");

                            Series serie = new Series(jsonObject.get("score").getAsString(),jsonObject.get("show").getAsJsonObject().get("name").getAsString(),url);
                            seriesList.add(i,serie);
                            //Log.e("SCORE SERIRE" , jsonObject.get("show").getAsJsonObject().getAsJsonObject("image").get("medium")+"");
                        }
                        recyclerView.setAdapter(seriesListAdapter);
                        seriesListAdapter.setOnItemClickListener(new SeriesListAdapter.onItemClickDetailListener() {
                            @Override
                            public void showDetails(String name, String image) {
                                iPresenter.showInformacion(name);
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("response fail",t.getMessage()+"");
            }
        });
        //iPresenter.showInformacion(id);

    }

    @Override
    public void setUpRecyclerViewSeries(RecyclerView recyclerView) {

    }


}
