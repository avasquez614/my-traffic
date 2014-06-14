package org.mytraffic.data.services.impl;

import org.mytraffic.api.Location;
import org.mytraffic.api.data.exceptions.DataApiException;
import org.mytraffic.api.data.services.LocationService;
import org.mytraffic.data.utils.maps.PolyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.api.data.services.LocationService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class LocationServiceImpl implements LocationService {

    private double pointWithinRouteTolerance;

    @Value("${locationOnRouteTolerance}")
    public void setPointWithinRouteTolerance(double pointWithinRouteTolerance) {
        this.pointWithinRouteTolerance = pointWithinRouteTolerance;
    }

    @Override
    public boolean isLocationOnRoute(Location location, String routePolyline) throws DataApiException {
        List<Location> decodedPolyline = PolyUtils.decode(routePolyline);

        return PolyUtils.isLocationOnPath(location, decodedPolyline, false, pointWithinRouteTolerance);
    }


}
