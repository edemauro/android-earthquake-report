package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mWebpage;

    public Earthquake(double magnitude, String location, long mTimeInMilliseconds, String webpage) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
        mWebpage = webpage;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getWebpage() {
        return mWebpage;
    }
}
