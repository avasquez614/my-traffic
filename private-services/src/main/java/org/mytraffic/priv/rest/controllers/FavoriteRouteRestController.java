package org.mytraffic.priv.rest.controllers;

import org.mytraffic.FavoriteRoute;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static org.mytraffic.priv.api.PrivateApiConstants.*;
import static org.mytraffic.utils.datetime.DateTimeUtils.TIME_FORMAT;

/**
 * REST controller for the {@link org.mytraffic.priv.api.services.FavoriteRouteService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping(BASE_URL_FAVORITE_ROUTES)
public class FavoriteRouteRestController {

    private FavoriteRouteService service;

    @Autowired
    public void setService(FavoriteRouteService service) {
        this.service = service;
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_FIND_BY_USER_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<FavoriteRoute> findFavoriteRoutesByUser(@RequestParam(PARAM_USER_ID) String userId)
            throws PrivateApiException {
        return service.findFavoriteRoutesByUserId(userId);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIME_RANGE, method = RequestMethod.GET)
    @ResponseBody
    List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(@RequestParam(value = PARAM_FROM, required = false)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime from,
                                                                  @RequestParam(value = PARAM_TO, required = false)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime to) throws PrivateApiException {
        return service.findFavoriteRoutesByNotificationTimeRange(from, to);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_ADD, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute addFavoriteRoute(@RequestBody FavoriteRoute route) throws PrivateApiException {
        return service.addFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute updateFavoriteRoute(@RequestBody FavoriteRoute route) throws PrivateApiException {
        return service.updateFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_REMOVE, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeFavoriteRoute(@PathVariable(PATH_VAR_ID) String id) throws PrivateApiException {
        service.removeFavoriteRoute(id);
    }

}
