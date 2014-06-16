package org.mytraffic.priv.api.services;

import org.mytraffic.Location;
import org.mytraffic.priv.api.exceptions.PrivateApiException;

/**
 * Utility service for resolution of map operations.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface LocationService {

    /**
     * Returns true if the specified {@link org.mytraffic.Location} lies on or is near the route.
     *
     * @param location  the location
     * @param polyline  the route, represented as a Google Map polyline
     *
     * @return true if the location is within the route, false otherwise
     */
    boolean isLocationOnRoute(Location location, String polyline) throws PrivateApiException;

}
