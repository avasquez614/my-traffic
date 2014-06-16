package org.mytraffic.priv.api.services;

import org.mytraffic.priv.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service for registration and querying of {@link org.mytraffic.priv.TrafficIncident}s.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface TrafficIncidentService {

    /**
     * Returns the incident with the specified ID.
     *
     * @param id    the ID associated to the incident
     *
     * @return the incident, or null if not found
     */
    TrafficIncident findIncidentById(String id) throws PrivateApiException;

    /**
     * Returns the list of traffic incidents whose last modified date is within the specified range.
     *
     * @param from  the start date of the range. A null value means to return incidents from the first one
     * @param to    the end date of the range. A null value means to return incidents until now
     *
     * @return the list of matching {@link org.mytraffic.priv.TrafficIncident}s
     */
    List<TrafficIncident> findIncidentsByDateRange(ZonedDateTime from, ZonedDateTime to) throws PrivateApiException;

    /**
     * Adds a new incident to the database.
     *
     * @param incident  the incident to add
     *
     * @return the added incident, with it's generated ID set
     */
    TrafficIncident addIncident(TrafficIncident incident) throws PrivateApiException;

    /**
     * Updates the specified incident in the database.
     *
     * @param incident  the incident to update
     *
     * @return the updated incident
     */
    TrafficIncident updateIncident(TrafficIncident incident) throws PrivateApiException;

    /**
     * Removes the incident with the specified ID from the database
     *
     * @param id    the ID of the incident to remove
     */
    void removeIncident(String id) throws PrivateApiException;

}
