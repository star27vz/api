package com.example.api_firms_nasa;

public class Fire {

    private double latitude;
    private double longitude;
    private String start_time;
    private String end_date;
    private String end_time;
    private String pixel_quality;
    private String confidence;
    private int fire_count;

    public double getLat() {
        return latitude;
    }

    public double getLon() {
        return longitude;
    }
}
