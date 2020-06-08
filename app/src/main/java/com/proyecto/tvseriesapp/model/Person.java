package com.proyecto.tvseriesapp.model;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.proyecto.tvseriesapp.adapter.PersonListAdapter;
import com.proyecto.tvseriesapp.adapter.SeriesListAdapter;
import com.proyecto.tvseriesapp.interfaces.INetwork;
import com.proyecto.tvseriesapp.interfaces.ITvSeriesHome;
import com.proyecto.tvseriesapp.view.EpisodesActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Person implements ITvSeriesHome.PeopleModel {

    private String name;
    private String birthday;
    private String image;
    private ITvSeriesHome.PeoplePresenter ipeoplePresenter;
    List<Person> personList;
    String url = "";

    public Person() {
    }

    public Person(String name, String birthday, String image) {
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }

    public Person(ITvSeriesHome.PeoplePresenter peoplePresenter) {
        this.ipeoplePresenter = peoplePresenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void findPeople(final RecyclerView recyclerView, String name) {
        personList = new ArrayList<>();
        EpisodesActivity episodesActivity = new EpisodesActivity();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(episodesActivity, 2);
        final PersonListAdapter personListAdapter = new PersonListAdapter(personList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INetwork tvApi = retrofit.create(INetwork.class);
        Call<JsonArray> call = tvApi.getPeopleByName(name);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        personList.clear();
                        for (int i = 0; i < response.body().size(); i++) {
                            JsonObject jsonObject = response.body().get(i).getAsJsonObject();
                            Log.e("personas", jsonObject + "");
                            /** check if the image exist**/
                            if (jsonObject.get("person").getAsJsonObject().get("image").isJsonNull()) {
                                url = "https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101028/112815904-stock-vector-no-image-available-icon-flat-vector-illustration.jpg?ver=6";
                            } else {
                                url = jsonObject.get("person").getAsJsonObject().getAsJsonObject("image").get("medium").getAsString();
                            }
                            Person person = new Person(jsonObject.get("person").getAsJsonObject().get("name").getAsString(),"",url);
                            personList.add(i, person);
                        }
                        recyclerView.setAdapter(personListAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("response fail", t.getMessage() + "");
            }
        });
    }
}
