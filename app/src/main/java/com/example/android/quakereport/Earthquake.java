package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private double mMagnitude;
    private String mCity;
    private Date mDate;

    public Earthquake(double magnitude, String city, Date date) {
        this.mMagnitude = magnitude;
        this.mCity = city;
        this.mDate = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getCity() {
        return mCity;
    }

    public Date getDate() {
        return mDate;
    }
}
