package com.example.fyp;

public class LocationHelper {
    private String longitude;
    private String latitude;

    public LocationHelper() {
        // Default constructor required for calls to DataSnapshot.getValue(LocationHelper.class)
    }

    public LocationHelper(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
