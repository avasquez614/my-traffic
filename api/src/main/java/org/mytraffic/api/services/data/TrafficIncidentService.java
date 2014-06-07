package org.mytraffic.api.services.data;

import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Service for registration and querying of traffic incidents.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface TrafficIncidentService {

    /**
     * Returns the list of traffic incidents whose last modified date is between the specified range.
     *
     * @param from  the start date of the range. A null value means to return incidents from the first one
     * @param to    the end date of the range. A null value means to return incidents until now
     *
     * @return the list of matching {@link org.mytraffic.api.TrafficIncident}s
     */
    List<TrafficIncident> findByDateRange(Date from, Date to) throws ServiceException;

    /**
     * Returns the incident with the specified ID.
     *
     * @param id    the ID associated to the incident
     *
     * @return the incident, or null if not found
     */
    TrafficIncident find(String id) throws ServiceException;

    /**
     * Adds a new incident to the database.
     *
     * @param incident  the incident to add
     *
     * @return the added incident, with it's generated ID set
     */
    TrafficIncident add(TrafficIncident incident) throws ServiceException;

    /**
     * Updates the specified incident in the database.
     *
     * @param incident  the incident to update
     *
     * @return the updated incident
     */
    TrafficIncident update(TrafficIncident incident) throws ServiceException;

}
