package org.mytraffic.rest.controllers;

import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.data.exceptions.DataServiceException;
import org.mytraffic.api.data.services.TrafficIncidentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static org.mytraffic.api.RestConstants.*;

/**
 * REST controller for the {@link org.mytraffic.api.data.services.TrafficIncidentDataService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@RestController
@RequestMapping(BASE_URL_DATA_TRAFFIC_INCIDENTS)
public class TrafficIncidentDataController {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Autowired
    private TrafficIncidentDataService trafficIncidentDataService;

    @RequestMapping(value = URL_DATA_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE, method = RequestMethod.GET)
    public List<TrafficIncident> findByDateRange(@RequestParam(value = PARAM_FROM, required = false)
                                                 @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date from,
                                                 @RequestParam(value = PARAM_TO, required = false)
                                                 @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date to)
            throws DataServiceException {
        return trafficIncidentDataService.findIncidentByDateTimeRange(from, to);
    }

}
