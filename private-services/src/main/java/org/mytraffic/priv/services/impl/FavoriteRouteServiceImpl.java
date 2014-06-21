package org.mytraffic.priv.services.impl;

import org.craftercms.commons.collections.IterableUtils;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorCode;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.FavoriteRouteService;
import org.mytraffic.priv.repositories.FavoriteRouteRepository;
import org.mytraffic.utils.datetime.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.priv.api.services.FavoriteRouteService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class FavoriteRouteServiceImpl implements FavoriteRouteService {

    @Autowired
    private FavoriteRouteRepository repository;

    @Override
    public List<FavoriteRoute> findFavoriteRoutesByUserId(String userId) throws PrivateApiException {
        try {
            return IterableUtils.toList(repository.findByUserId(userId));
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to find favorite " +
                    "routes by user ID '" + userId + "'", e);
        }
    }

    @Override
    public List<FavoriteRoute> findFavoriteRoutesByNotificationTimeRange(LocalTime from, LocalTime to)
            throws PrivateApiException {
        try {
            return IterableUtils.toList(repository.findByNotificationTimeRange(from, to));
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to find favorite " +
                    "routes by notification time range [" + DateTimeUtils.formatTime(from) + ", " + DateTimeUtils
                    .formatTime(to) + "]", e);
        }
    }

    @Override
    public FavoriteRoute addFavoriteRoute(FavoriteRoute route) throws PrivateApiException {
        route.setId(null);

        try {
            repository.insert(route);
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to add favorite route", e);
        }

        return route;
    }

    @Override
    public FavoriteRoute updateFavoriteRoute(FavoriteRoute route) throws PrivateApiException {
        try {
            if (repository.findById(route.getId().toString()) != null) {
                repository.save(route);

                return route;
            } else {
                throw new PrivateApiException(PrivateApiErrorCode.NO_SUCH_FAVORITE_ROUTE, "Can't update favorite " +
                        "route '" + route.getId() + "' because it doesn't exist in the database");
            }
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to update favorite " +
                    "route '" + route.getId() + "'", e);
        }
    }

    @Override
    public void removeFavoriteRoute(String id) throws PrivateApiException {
        try {
            repository.removeById(id);
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to remove favorite " +
                    "route '" + id + "'", e);
        }
    }

}
