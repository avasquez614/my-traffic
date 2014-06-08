package org.mytraffic.data.rest.controllers;

import org.mytraffic.api.FavoriteRoute;
import org.mytraffic.api.data.exceptions.DataServiceException;
import org.mytraffic.api.data.services.FavoriteRouteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import static org.mytraffic.api.data.DataRestConstants.*;

/**
 * REST controller for the {@link org.mytraffic.api.data.services.FavoriteRouteDataService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping(BASE_URL_FAVORITE_ROUTES)
public class FavoriteRouteDataRestController {

    @Autowired
    private FavoriteRouteDataService favoriteRouteDataService;

    @RequestMapping(value = URL_FAVORITE_ROUTES_FIND_BY_USER_ID, method = RequestMethod.GET)
    @ResponseBody
    public List<FavoriteRoute> findFavoriteRoutesForUser(@RequestParam(PARAM_USER_ID) String userId)
            throws DataServiceException {
        return favoriteRouteDataService.findFavoriteRoutesByUserId(userId);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_FIND_BY_NOTIFICATION_TIMES_RANGE, method = RequestMethod.GET)
    @ResponseBody
    List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(@RequestParam(PARAM_FROM)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime from,
                                                                  @RequestParam(PARAM_TO)
                                                                  @DateTimeFormat(pattern = TIME_FORMAT)
                                                                  LocalTime to) throws DataServiceException {
        return favoriteRouteDataService.findFavoriteRoutesByNotificationTimeRange(from, to);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_ADD, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute addFavoriteRoute(@RequestBody FavoriteRoute route) throws DataServiceException {
        return favoriteRouteDataService.addFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public FavoriteRoute updateFavoriteRoute(@RequestBody FavoriteRoute route) throws DataServiceException {
        return favoriteRouteDataService.updateFavoriteRoute(route);
    }

    @RequestMapping(value = URL_FAVORITE_ROUTES_REMOVE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void removeFavoriteRoute(@PathVariable(PATH_VAR_ID) String id) throws DataServiceException {
        favoriteRouteDataService.removeFavoriteRoute(id);
    }

}
