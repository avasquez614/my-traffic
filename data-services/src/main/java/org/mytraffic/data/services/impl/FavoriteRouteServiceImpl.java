package org.mytraffic.data.services.impl;

import org.craftercms.commons.collections.IterableUtils;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.FavoriteRoute;
import org.mytraffic.api.data.exceptions.DataApiErrorCode;
import org.mytraffic.api.data.exceptions.DataApiException;
import org.mytraffic.api.data.services.FavoriteRouteService;
import org.mytraffic.data.repositories.FavoriteRouteRepository;
import org.mytraffic.data.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.api.data.services.FavoriteRouteService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class FavoriteRouteServiceImpl implements FavoriteRouteService {

    @Autowired
    private FavoriteRouteRepository repository;

    @Override
    public List<FavoriteRoute> findFavoriteRoutesByUserId(String userId) throws DataApiException {
        try {
            return IterableUtils.toList(repository.findByUserId(userId));
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to find favorite " +
                    "routes by user ID '" + userId + "'", e);
        }
    }

    @Override
    public List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(LocalTime from, LocalTime to)
            throws DataApiException {
        try {
            return IterableUtils.toList(repository.findByNotificationTimeRange(from, to));
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to find favorite " +
                    "routes by notification time range [" + DateTimeUtils.formatTime(from) + ", " + DateTimeUtils
                    .formatTime(to) + "]", e);
        }
    }

    @Override
    public FavoriteRoute addFavoriteRoute(FavoriteRoute route) throws DataApiException {
        route.setId(null);

        try {
            repository.insert(route);
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to add favorite route", e);
        }

        return route;
    }

    @Override
    public FavoriteRoute updateFavoriteRoute(FavoriteRoute route) throws DataApiException {
        try {
            if (repository.findById(route.getId().toString()) != null) {
                repository.save(route);

                return route;
            } else {
                throw new DataApiException(DataApiErrorCode.NO_SUCH_FAVORITE_ROUTE, "Can't update favorite " +
                        "route '" + route.getId() + "' because it doesn't exist in the database");
            }
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to update favorite " +
                    "route '" + route.getId() + "'", e);
        }
    }

    @Override
    public void removeFavoriteRoute(String id) throws DataApiException {
        try {
            repository.removeById(id);
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to remove favorite " +
                    "route '" + id + "'", e);
        }
    }

}
