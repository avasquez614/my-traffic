package org.mytraffic.priv.rest.controllers;

import org.mytraffic.priv.Location;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.mytraffic.priv.api.PrivateApiConstants.*;

/**
 * REST controller for the {@link org.mytraffic.priv.api.services.LocationService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping(BASE_URL_LOCATION)
public class LocationRestController {

    private LocationService locationService;

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = URL_LOCATION_LOCATION_ON_ROUTE, method = RequestMethod.GET)
    @ResponseBody
    public boolean isLocationOnRoute(@RequestParam(PARAM_LATITUDE) double latitude,
                                     @RequestParam(PARAM_LONGITUDE) double longitude,
                                     @RequestParam(PARAM_POLYLINE) String polyline) throws PrivateApiException {
        return locationService.isLocationOnRoute(new Location(latitude, longitude), polyline);
    }

}
