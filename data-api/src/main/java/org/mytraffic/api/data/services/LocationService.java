package org.mytraffic.api.data.services;

import org.mytraffic.api.Location;
import org.mytraffic.api.data.exceptions.DataApiException;

/**
 * Utility service for resolution of map operations.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface LocationService {

    /**
     * Returns true if the specified {@link org.mytraffic.api.Location} lies on or is near the route.
     *
     * @param location      the location
     * @param routePolyline the route, represented as a Google Map polyline
     *
     * @return true if the location is within the route, false otherwise
     */
    boolean isLocationOnRoute(Location location, String routePolyline) throws DataApiException;

}
