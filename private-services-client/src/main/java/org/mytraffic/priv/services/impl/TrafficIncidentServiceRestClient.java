package org.mytraffic.priv.services.impl;

import org.craftercms.commons.rest.RestClientUtils;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.mytraffic.utils.datetime.DateTimeUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mytraffic.priv.api.PrivateApiConstants.*;

/**
 * REST client implementation of {@link org.mytraffic.priv.api.services.TrafficIncidentService}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncidentServiceRestClient extends AbstractPrivateServicesRestClientBase implements TrafficIncidentService {

    public static final ParameterizedTypeReference<List<TrafficIncident>> trafficIncidentListTypeRef =
            new ParameterizedTypeReference<List<TrafficIncident>>() {};

    @Override
    public TrafficIncident findIncidentById(String id) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_TRAFFIC_INCIDENTS_FIND_BY_ID);

        return doGetForObject(url, TrafficIncident.class, id);
    }

    @Override
    public List<TrafficIncident> findIncidentsByDateRange(ZonedDateTime from, ZonedDateTime to) throws PrivateApiException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        RestClientUtils.addValue(PARAM_FROM, DateTimeUtils.formatDateTime(from), params);
        RestClientUtils.addValue(PARAM_TO, DateTimeUtils.formatDateTime(to), params);

        String url = getAbsoluteUrl(URL_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE);
        url = RestClientUtils.addQueryParams(url, params, false);

        return doGetForObject(url, trafficIncidentListTypeRef);
    }

    @Override
    public TrafficIncident addIncident(TrafficIncident incident) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_TRAFFIC_INCIDENTS_ADD);

        return doPostForObject(url, incident, TrafficIncident.class);
    }

    @Override
    public TrafficIncident updateIncident(TrafficIncident incident) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_TRAFFIC_INCIDENTS_UPDATE);

        return doPostForObject(url, incident, TrafficIncident.class);
    }

    @Override
    public void removeIncident(String id) throws PrivateApiException {
        String url = getAbsoluteUrl(URL_TRAFFIC_INCIDENTS_REMOVE);

        doDelete(url, id);
    }

}
