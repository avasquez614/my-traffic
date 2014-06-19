package org.mytraffic.notifications;

import org.craftercms.commons.mail.Email;
import org.craftercms.commons.mail.EmailException;
import org.craftercms.commons.mail.EmailFactory;
import org.craftercms.profile.api.Profile;
import org.craftercms.profile.api.exceptions.ProfileException;
import org.craftercms.profile.api.services.ProfileService;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.mytraffic.priv.api.services.LocationService;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(NotificationJob.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int delay;
    private int timeSinceIncidents;
    private String from;
    private String subject;
    private String templateName;
    private EmailFactory emailFactory;
    private FavoriteRouteService favoriteRouteService;
    private TrafficIncidentService trafficIncidentService;
    private LocationService locationService;
    private ProfileService profileService;

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

    @Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Scheduled(cron = "${notification.job.cron}")
    public void sendNotifications()  {
        ZonedDateTime startTime = ZonedDateTime.now();

        logger.info("Notification job started");

        try {
            List<FavoriteRoute> routes = getRoutesToNotify(startTime);
            List<TrafficIncident> incidents = getTrafficIncidentsToNotify(startTime);
            MultiValueMap<String, RouteWithIncidents> routesWithIncidents = getRoutesWithIncidents(routes, incidents);

            if (!routesWithIncidents.isEmpty()) {
                for (Map.Entry<String, List<RouteWithIncidents>> entry : routesWithIncidents.entrySet()) {
                   Profile profile = null;
                    try {
                        profile = profileService.getProfile(entry.getKey());
                        Map<String, Object> templateModel = new HashMap<>();

                        templateModel.put("profile", profile);
                        templateModel.put("routesWithIncidents", entry.getValue());
                        templateModel.put("formatter", FORMATTER);

                        sendEmail(profile.getEmail(), templateModel);
                    } catch (ProfileException e) {
                        logger.error("Unable to retrieve profile for user ID '" + entry.getKey() + "'", e);
                    } catch (EmailException e) {
                        logger.error("Unable to send email to " + profile.getEmail(), e);
                    }
                }
            }
        } catch (PrivateApiException e) {
            logger.error("Error while trying to resolve routes with incidents", e);
        }

        logger.info("Notification job ended");
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

    private MultiValueMap<String, RouteWithIncidents> getRoutesWithIncidents(List<FavoriteRoute> routes,
                                                                             List<TrafficIncident> incidents)
            throws PrivateApiException {
        MultiValueMap<String, RouteWithIncidents> routesWithIncidents = new LinkedMultiValueMap<>();

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
                    routesWithIncidents.add(route.getUserId(), new RouteWithIncidents(route, incidents));
                }
            }
        }

        return routesWithIncidents;
    }

    private static class RouteWithIncidents {

        private FavoriteRoute route;
        private List<TrafficIncident> incidents;

        private RouteWithIncidents(FavoriteRoute route, List<TrafficIncident> incidents) {
            this.route = route;
            this.incidents = incidents;
        }

        public FavoriteRoute getRoute() {
            return route;
        }

        public List<TrafficIncident> getIncidents() {
            return incidents;
        }

    }

    private void sendEmail(String to, Object templateModel) throws EmailException {
        String[] toArray = new String[] { to };
        Email email = emailFactory.getEmail(from, toArray, null, null, subject, templateName, templateModel, true);

        email.send();
    }

}
