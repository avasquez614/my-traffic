package org.mytraffic.pub.rest.controllers;

import org.craftercms.security.utils.SecurityUtils;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.IncidentSeverity;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.mytraffic.utils.datetime.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalTime;
import java.util.List;

/**
 * REST controller for traffic related operations.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping("/api/1/routes")
public class RouteRestController {

    @Autowired
    private FavoriteRouteService favoriteRouteService;

    @RequestMapping(value = "/favorite/register", method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute registerFavoriteRoute(@RequestParam("polyline") String polyline,
                                               @RequestParam("description") String description,
                                               @DateTimeFormat(pattern = DateTimeUtils.TIME_FORMAT)
                                               @RequestParam("notificationTime") List<LocalTime> notificationTimes,
                                               @RequestParam("minIncidentSeverity")IncidentSeverity miIncidentSeverity,
                                               HttpServletRequest request)
            throws PrivateApiException{
        String userId = SecurityUtils.getAuthentication(request).getProfile().getId().toString();

        FavoriteRoute route = new FavoriteRoute();
        route.setUserId(userId);
        route.setPolyline(polyline);
        route.setDescription(description);
        route.setNotificationTimes(notificationTimes);
        route.setMinIncidentSeverity(miIncidentSeverity);

        return favoriteRouteService.addFavoriteRoute(route);
    }

}
