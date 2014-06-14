package org.mytraffic.data.rest.controllers;

import org.mytraffic.api.FavoriteRoute;
import org.mytraffic.api.data.exceptions.DataApiException;
import org.mytraffic.api.data.services.FavoriteRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static org.mytraffic.api.data.DataApiConstants.*;

/**
 * REST controller for the {@link org.mytraffic.api.data.services.FavoriteRouteService}.
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
            throws DataApiException {
        return service.findFavoriteRoutesByUserId(userId);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIME_RANGE, method = RequestMethod.GET)
    @ResponseBody
    List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(@RequestParam(PARAM_FROM)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime from,
                                                                  @RequestParam(PARAM_TO)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime to) throws DataApiException {
        return service.findFavoriteRoutesByNotificationTimeRange(from, to);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_ADD, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute addFavoriteRoute(@RequestBody FavoriteRoute route) throws DataApiException {
        return service.addFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute updateFavoriteRoute(@RequestBody FavoriteRoute route) throws DataApiException {
        return service.updateFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_REMOVE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void removeFavoriteRoute(@PathVariable(PATH_VAR_ID) String id) throws DataApiException {
        service.removeFavoriteRoute(id);
    }

}
