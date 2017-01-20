package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

public class Earthquake {
    private double mMagnitude;
    private String mCity;
    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String city, long mTimeInMilliseconds) {
        this.mMagnitude = magnitude;
        this.mCity = city;
        this.mTimeInMilliseconds = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getCity() {
        return mCity;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
