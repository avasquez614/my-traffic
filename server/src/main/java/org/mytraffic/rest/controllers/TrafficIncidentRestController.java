package org.mytraffic.rest.controllers;

import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.services.data.TrafficIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * REST controller for the {@link org.mytraffic.api.services.data.TrafficIncidentService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@RestController
@RequestMapping("/api/1/traffic/incident")
public class TrafficIncidentRestController {

    @Autowired
    private TrafficIncidentService trafficIncidentService;

    @RequestMapping(value = "/by_date_range", method = RequestMethod.GET)
    public List<TrafficIncident> findByDateRange(@RequestParam(value = "from", required = false)
                                                 @DateTimeFormat(pattern = TrafficIncident.DATE_TIME_FORMAT) Date from,
                                                 @RequestParam(value = "to", required = false)
                                                 @DateTimeFormat(pattern = TrafficIncident.DATE_TIME_FORMAT) Date to) {
        return trafficIncidentService.findByDateRange(from, to);
    }

}
