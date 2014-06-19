package org.mytraffic.notifications;

import org.craftercms.commons.mail.EmailFactory;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.mytraffic.priv.api.services.LocationService;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Runs every certain fixed delay to generate traffic incident notifications for users that have registered
 * favorite routes.
 *
 * @author avasquez
 * @author mariobarque
 */
@Component
public class NotificationJob {

    private int delay;
    private int timeSinceIncidents;
    private String from;
    private String subject;
    private String templateName;
    private EmailFactory emailFactory;
    private FavoriteRouteService favoriteRouteService;
    private TrafficIncidentService trafficIncidentService;
    private LocationService locationService;

    @Value("${notification.job.delay}")
    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Value("${notification.job.timeSinceIncidents}")
    public void setTimeSinceIncidents(int timeSinceIncidents) {
        this.timeSinceIncidents = timeSinceIncidents;
    }

    @Value("${mail.from}")
    public void setFrom(String from) {
        this.from = from;
    }

    @Value("${mail.subject}")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Value("${mail.templateName}")
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Autowired
    public void setEmailFactory(EmailFactory emailFactory) {
        this.emailFactory = emailFactory;
    }

    @Autowired
    public void setFavoriteRouteService(FavoriteRouteService favoriteRouteService) {
        this.favoriteRouteService = favoriteRouteService;
    }

    @Autowired
    public void setTrafficIncidentService(TrafficIncidentService trafficIncidentService) {
        this.trafficIncidentService = trafficIncidentService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Scheduled(cron = "${notification.job.cron}")
    public void sendNotifications() throws PrivateApiException {
        ZonedDateTime startTime = ZonedDateTime.now();

        List<FavoriteRoute> routes = getRoutesToNotify(startTime);
        List<TrafficIncident> incidents = getTrafficIncidentsToNotify(startTime);
        Map<String, List<TrafficIncident>> routesWithIncidents = getRoutesWithIncidents(routes, incidents);

        if (!routesWithIncidents.isEmpty()) {

        }
    }

    private List<FavoriteRoute> getRoutesToNotify(ZonedDateTime jobStartTime) throws PrivateApiException {
        ZonedDateTime beforeNextExecution = jobStartTime.plusMinutes(delay).minusSeconds(1);

        LocalTime from = jobStartTime.toLocalTime();
        LocalTime to = beforeNextExecution.toLocalTime();

        return favoriteRouteService.findFavoriteRoutesByNotificationTimeRange(from, to);
    }

    private List<TrafficIncident> getTrafficIncidentsToNotify(ZonedDateTime jobStartTime) throws PrivateApiException {
        ZonedDateTime startOfIncidents = jobStartTime.minusMinutes(timeSinceIncidents);

        return trafficIncidentService.findIncidentsByDateRange(startOfIncidents, null);
    }

    private Map<String, List<TrafficIncident>> getRoutesWithIncidents(List<FavoriteRoute> routes,
                                                                      List<TrafficIncident> incidents)
            throws PrivateApiException {
        Map<String, List<TrafficIncident>> routesWithIncidents = new LinkedHashMap<>();

        if (routes != null) {
            for (FavoriteRoute route : routes) {
                String polyline = route.getPolyline();
                List<TrafficIncident> incidentsInRoute = new ArrayList<>();

                if (incidents != null) {
                    for (TrafficIncident incident : incidents) {
                        boolean incidentInRoute = locationService.isLocationOnRoute(incident.getLocation(), polyline);
                        if (incidentInRoute) {
                            incidentsInRoute.add(incident);
                        }
                    }
                }

                if (!incidentsInRoute.isEmpty()) {
                    routesWithIncidents.put(route.getDescription(), incidentsInRoute);
                }
            }
        }

        return routesWithIncidents;
    }

}
