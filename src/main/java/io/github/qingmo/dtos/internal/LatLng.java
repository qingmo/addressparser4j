package io.github.qingmo.dtos.internal;

public class LatLng {
    private String latitude;
    private String longitude;

    public LatLng(String longitude, String latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
