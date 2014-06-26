package org.mytraffic.pub.rest.controllers;

import org.mytraffic.IncidentSeverity;
import org.mytraffic.IncidentType;
import org.mytraffic.Location;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.ZonedDateTime;

/**
 * REST controller for traffic related operations.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping("/api/1/traffic")
public class TrafficRestController {

    @Autowired
    private TrafficIncidentService trafficIncidentService;

    @RequestMapping(value = "/incident/create", method = RequestMethod.POST)
    @ResponseBody
    public TrafficIncident createIncident(@RequestParam("latitude") double latitude,
                                          @RequestParam("longitude") double longitude,
                                          @RequestParam("type")IncidentType type,
                                          @RequestParam("severity") IncidentSeverity severity,
                                          @RequestParam("description") String description) throws PrivateApiException {
        ZonedDateTime now = ZonedDateTime.now();

        TrafficIncident incident = new TrafficIncident();
        incident.setLocation(new Location(latitude, longitude));
        incident.setType(type);
        incident.setSeverity(severity);
        incident.setDescription(description);
        incident.setStart(now);
        incident.setLastModified(now);

        return trafficIncidentService.addIncident(incident);
    }

}
