package org.mytraffic.api.data.services;

import org.mytraffic.api.FavoriteRoute;
import org.mytraffic.api.data.exceptions.DataApiException;

import java.time.LocalTime;
import java.util.List;

/**
 * Service for registration and querying of {@link org.mytraffic.api.FavoriteRoute}s.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface FavoriteRouteService {

    /**
     * Returns the favorites routes registered to a user.
     *
     * @param userId    the user's ID
     *
     * @return  the favorite routes of the user
     */
    List<FavoriteRoute> findFavoriteRoutesByUserId(String userId) throws DataApiException;

    /**
     * Returns the list of favorite routes with one or more notification times within the specified range.
     *
     * @param from  the start time of the range. A null value means to return incidents from the start of the day
     * @param to    the end date of the range. A null value means to return incidents until the end of day
     *
     * @return the list of matching {@link org.mytraffic.api.FavoriteRoute}s
     */
    List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(LocalTime from, LocalTime to) throws DataApiException;

    /**
     * Adds a new favorite route to the database.
     *
     * @param route  the route to add
     *
     * @return the added route, with it's generated ID set
     */
    FavoriteRoute addFavoriteRoute(FavoriteRoute route) throws DataApiException;

    /**
     * Updates the specified favorite route in the database.
     *
     * @param route  the route to update
     *
     * @return the updated route
     */
    FavoriteRoute updateFavoriteRoute(FavoriteRoute route) throws DataApiException;

    /**
     * Removes the favorite route with the specified ID from the database
     *
     * @param id    the ID of the route to remove
     */
    void removeFavoriteRoute(String id) throws DataApiException;

}
