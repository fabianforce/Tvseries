package com.proyecto.tvseriesapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.proyecto.tvseriesapp.R;

public class SeriesDetailsActivity extends AppCompatActivity {
    String name;
    TextView textViewName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        textViewName = findViewById(R.id.name_text);
        textViewName.setText(name);
    }
}
