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
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6&limit=10";


    public List<Earthquake> mEarthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            return QueryUtils.extractEarthquakes(urls[0]);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            if(earthquakes == null) {
                return;
            }

            updateUI(earthquakes);
        }
    }

    private void updateUI(List<Earthquake> earthquakes) {
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
