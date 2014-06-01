package org.mytraffic.api;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Represents a traffic incident (accident, construction, etc.)
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncident {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private String id;
    private MapPoint location;
    private IncidentType type;
    private IncidentSeverity severity;
    private String description;
    private Date start;
    private Date lastModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MapPoint getLocation() {
        return location;
    }

    public void setLocation(MapPoint location) {
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

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}
