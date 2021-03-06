package org.mytraffic.priv.rest.controllers;

import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mytraffic.priv.api.PrivateApiConstants.*;
import static org.mytraffic.utils.datetime.DateTimeUtils.DATE_TIME_FORMAT;

/**
 * REST controller for the {@link org.mytraffic.priv.api.services.TrafficIncidentService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping(BASE_URL_TRAFFIC_INCIDENTS)
public class TrafficIncidentRestController {

    private TrafficIncidentService service;

    @Autowired
    public void setService(TrafficIncidentService service) {
        this.service = service;
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_FIND_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public TrafficIncident findIncidentById(@PathVariable(PATH_VAR_ID) String id) throws PrivateApiException {
        return service.findIncidentById(id);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_FIND_BY_DATE_RANGE, method = RequestMethod.GET)
    @ResponseBody
    public List<TrafficIncident> findIncidentsByDateRange(@RequestParam(value = PARAM_FROM, required = false)
                                                          @DateTimeFormat(pattern = DATE_TIME_FORMAT)
                                                          ZonedDateTime from,
                                                          @RequestParam(value = PARAM_TO, required = false)
                                                          @DateTimeFormat(pattern = DATE_TIME_FORMAT)
                                                          ZonedDateTime to) throws PrivateApiException {
        return service.findIncidentsByDateRange(from, to);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_ADD, method = RequestMethod.POST)
    @ResponseBody
    public TrafficIncident addIncident(@RequestBody TrafficIncident incident) throws PrivateApiException {
        return service.addIncident(incident);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public TrafficIncident updateIncident(@RequestBody TrafficIncident incident) throws PrivateApiException {
        return service.updateIncident(incident);
    }

    @RequestMapping(value = URL_TRAFFIC_INCIDENTS_REMOVE, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeIncident(@PathVariable(PATH_VAR_ID) String id) throws PrivateApiException {
        service.removeIncident(id);
    }

}
