package org.mytraffic.api;

/**
 * Represents a geographical point in a map, with latitude and longitude.
 *
 * @author avasquez
 * @author mariobarque
 */
public class MapPoint {

    private double latitude;
    private double longitude;

    public MapPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
