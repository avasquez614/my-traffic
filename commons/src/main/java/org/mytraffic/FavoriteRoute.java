package org.mytraffic;

import org.bson.types.ObjectId;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a route that a user registers to receive notifications of incidents along it.
 *
 * @author avasquez
 * @author mariobarque
 */
public class FavoriteRoute {

    private ObjectId _id;
    private String userId;
    private String polyline;
    private String description;
    private List<LocalTime> notificationTimes;
    private IncidentSeverity minIncidentSeverity;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FavoriteRoute route = (FavoriteRoute) o;

        if (!_id.equals(route._id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

}
