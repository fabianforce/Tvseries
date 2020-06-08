package com.proyecto.tvseriesapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tvseriesapp.R;
import com.proyecto.tvseriesapp.model.Person;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonHolder> {

    List<Person> personList;
    private onItemClickPersonListener personListener;

    public PersonListAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder personHolder, final int position) {
        personHolder.textViewName.setText(personList.get(position).getName());
        personHolder.personImage(personList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView personImageView;
        View mview;

        public PersonHolder(@NonNull final View itemView) {
            super(itemView);
            mview = itemView;
            textViewName = itemView.findViewById(R.id.person_name_txt);
            personImageView = itemView.findViewById(R.id.profile_image);
        }

        public void personImage(String url) {
            Picasso.get().load(url).placeholder(R.drawable.loader).into(personImageView);
        }

    }

    public interface onItemClickPersonListener {
        void showPersonDetails(String name, Person person);
    }

    public void setOnItemClickListener(onItemClickPersonListener onItemClickListener) {
        this.personListener = onItemClickListener;
    }
}