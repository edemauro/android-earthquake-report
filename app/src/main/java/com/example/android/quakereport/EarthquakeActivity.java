package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6&limit=10";


    public ArrayList<Earthquake> mEarthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            return mEarthquakes = QueryUtils.extractEarthquakes(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            updateUI(earthquakes);
        }
    }

    private void updateUI(ArrayList<Earthquake> earthquakes) {
        mEarthquakes = earthquakes;

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, mEarthquakes);

        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Earthquake currentEarthquake = adapter.getItem(i);

                Uri earthquakeUri = Uri.parse(currentEarthquake.getWebpage());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                startActivity(websiteIntent);
            }
        });
    }
}
