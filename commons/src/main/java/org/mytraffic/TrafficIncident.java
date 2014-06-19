package org.mytraffic;

import org.bson.types.ObjectId;

import java.time.ZonedDateTime;

/**
 * Represents a traffic incident (accident, construction, etc.)
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncident {

    private ObjectId _id;
    private Location location;
    private IncidentType type;
    private IncidentSeverity severity;
    private String description;
    private ZonedDateTime start;
    private ZonedDateTime lastModified;

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public IncidentType getType() {
        return type;
    }

    public void setType(IncidentType type) {
        this.type = type;
    }

    public IncidentSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(IncidentSeverity severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrafficIncident incident = (TrafficIncident) o;

        if (!_id.equals(incident._id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

}
