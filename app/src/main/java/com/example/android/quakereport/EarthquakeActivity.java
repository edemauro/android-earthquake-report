package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake(7.2, "San Francisco",
                new Date(2016 - 1900, 2, 2)));
        earthquakes.add(new Earthquake(6.1, "London",
                new Date(2015 - 1900, 7, 20)));
        earthquakes.add(new Earthquake(3.9, "Tokyo",
                new Date(2014 - 1900, 11, 10)));
        earthquakes.add(new Earthquake(5.4, "Mexico City",
                new Date(2014 - 1900, 5, 3)));
        earthquakes.add(new Earthquake(2.8, "Moscow",
                new Date(2013 - 1900, 1, 31)));
        earthquakes.add(new Earthquake(4.9, "Rio de Janeiro",
                new Date(2012 - 1900, 8, 19)));
        earthquakes.add(new Earthquake(1.6, "Paris",
                new Date(2011 - 1900, 10, 30)));

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        earthquakeListView.setAdapter(adapter);
    }
}
