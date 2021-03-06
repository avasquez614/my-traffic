package org.mytraffic.priv.services.impl;

import org.craftercms.commons.rest.RestClientUtils;
import org.mytraffic.Location;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.LocationService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mytraffic.priv.api.PrivateApiConstants.*;

/**
 * REST client implementation of {@link org.mytraffic.priv.api.services.LocationService}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class LocationServiceRestClient extends AbstractPrivateServicesRestClientBase implements LocationService {

    @Override
    public boolean isLocationOnRoute(Location location, String polyline) throws PrivateApiException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        RestClientUtils.addValue(PARAM_LATITUDE, location.getLatitude(), params);
        RestClientUtils.addValue(PARAM_LONGITUDE, location.getLongitude(), params);
        RestClientUtils.addValue(PARAM_POLYLINE, polyline, params);

        String url = getAbsoluteUrl(BASE_URL_LOCATION + URL_LOCATION_LOCATION_ON_ROUTE);
        url = RestClientUtils.addQueryParams(url, params, true);

        try {
            return doGetForObject(new URI(url), Boolean.class);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Invalid URI: " + url);
        }
    }

}
