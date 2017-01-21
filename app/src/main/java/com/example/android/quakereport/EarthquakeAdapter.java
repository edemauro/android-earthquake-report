package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = "EarthquakeAdapter";
    private Earthquake mEarthquake;

    public EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        mEarthquake = getItem(position);
        String[] locationParts;

        if(mEarthquake.getLocation().contains(" of ")) {
            locationParts = mEarthquake.getLocation().split(" of ");
        } else {
            locationParts = new String[]{"Near the", mEarthquake.getLocation()};
        }

        String magnitude = formatMagnitude(mEarthquake.getMagnitude());
        Date dateObject = new Date(mEarthquake.getTimeInMilliseconds());
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);

        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeView.setText(magnitude);

        TextView offsetView = (TextView) listItemView.findViewById(R.id.offset);
        offsetView.setText(locationParts[0]);

        TextView cityView = (TextView) listItemView.findViewById(R.id.city);
        cityView.setText(locationParts[1]);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        int magntiudeColor = getMagnitudeColor(mEarthquake.getMagnitude());

        magnitudeCircle.setColor(magntiudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;

        if(magnitude < 2) {
            magnitudeColorResourceId = R.color.magnitude1;
        } else if(magnitude < 3) {
            magnitudeColorResourceId = R.color.magnitude2;
        } else if(magnitude < 4) {
            magnitudeColorResourceId = R.color.magnitude3;
        } else if(magnitude < 5) {
            magnitudeColorResourceId = R.color.magnitude4;
        } else if(magnitude < 6) {
            magnitudeColorResourceId = R.color.magnitude5;
        } else if(magnitude < 7) {
            magnitudeColorResourceId = R.color.magnitude6;
        } else if(magnitude < 8) {
            magnitudeColorResourceId = R.color.magnitude7;
        } else if(magnitude < 9) {
            magnitudeColorResourceId = R.color.magnitude8;
        } else if(magnitude < 10) {
            magnitudeColorResourceId = R.color.magnitude9;
        } else {
            magnitudeColorResourceId = R.color.magnitude10plus;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");

        return formatter.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");

        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");

        return dateFormat.format(dateObject);
    }
}
