package org.mytraffic.api.services;

import org.mytraffic.api.TrafficIncident;

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
    List<TrafficIncident> findByDateRange(Date from, Date to);

}
