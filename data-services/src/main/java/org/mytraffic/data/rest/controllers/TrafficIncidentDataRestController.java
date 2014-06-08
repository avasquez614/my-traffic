package org.mytraffic.data.rest.controllers;

import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.data.exceptions.DataServiceException;
import org.mytraffic.api.data.services.TrafficIncidentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.mytraffic.api.data.DataRestConstants.*;

/**
 * REST controller for the {@link org.mytraffic.api.data.services.TrafficIncidentDataService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping(BASE_URL_TRAFFIC_INCIDENTS)
public class TrafficIncidentDataRestController {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Autowired
    private TrafficIncidentDataService trafficIncidentDataService;

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_FIND_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public TrafficIncident findIncidentById(@PathVariable(PATH_VAR_ID) String id) throws DataServiceException {
        return trafficIncidentDataService.findIncidentById(id);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE, method = RequestMethod.GET)
    @ResponseBody
    public List<TrafficIncident> findByDateRange(@RequestParam(value = PARAM_FROM, required = false)
                                                 @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date from,
                                                 @RequestParam(value = PARAM_TO, required = false)
                                                 @DateTimeFormat(pattern = DATE_TIME_FORMAT) Date to)
            throws DataServiceException {
        return trafficIncidentDataService.findIncidentByDateTimeRange(from, to);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_ADD, method = RequestMethod.POST)
    @ResponseBody
    public TrafficIncident addIncident(@RequestBody TrafficIncident incident) throws DataServiceException {
        return trafficIncidentDataService.addIncident(incident);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public TrafficIncident updateIncident(@RequestBody TrafficIncident incident) throws DataServiceException {
        return trafficIncidentDataService.updateIncident(incident);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_REMOVE, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeIncident(@PathVariable(PATH_VAR_ID) String id) throws DataServiceException {
        trafficIncidentDataService.removeIncident(id);
    }

}
