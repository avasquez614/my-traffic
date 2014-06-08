package org.mytraffic.api;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a route that a user registers to receive notifications of incidents along it.
 *
 * @author avasquez
 * @author mariobarque
 */
public class FavoriteRoute {

    private String polyline;
    private String description;
    private List<LocalTime> notificationTimes;
    private IncidentSeverity minIncidentSeverity;

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LocalTime> getNotificationTimes() {
        return notificationTimes;
    }

    public void setNotificationTimes(List<LocalTime> notificationTimes) {
        this.notificationTimes = notificationTimes;
    }

    public IncidentSeverity getMinIncidentSeverity() {
        return minIncidentSeverity;
    }

    public void setMinIncidentSeverity(IncidentSeverity minIncidentSeverity) {
        this.minIncidentSeverity = minIncidentSeverity;
    }

}
