package org.mytraffic.priv.services.impl;

import org.mytraffic.Location;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.LocationService;
import org.mytraffic.priv.utils.maps.PolyUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.priv.api.services.LocationService}.
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
    public boolean isLocationOnRoute(Location location, String polyline) throws PrivateApiException {
        List<Location> decodedPolyline = PolyUtils.decode(polyline);

        return PolyUtils.isLocationOnPath(location, decodedPolyline, false, pointWithinRouteTolerance);
    }


}
