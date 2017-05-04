package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by Sudhanshu on 04-05-2017.
 */

public class Earthquake {

    //Data members
    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    //Constructor
    public Earthquake(double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    //Accessor methods
    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getDate() {
        return mDate;
    }

    public String getURL() {
        return mUrl;
    }
}
