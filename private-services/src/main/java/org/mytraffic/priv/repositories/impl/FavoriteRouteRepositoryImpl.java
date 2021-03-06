package org.mytraffic.priv.repositories.impl;

import org.craftercms.commons.mongo.AbstractJongoRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.FavoriteRoute;
import org.mytraffic.priv.repositories.FavoriteRouteRepository;

import java.time.LocalTime;
import java.util.Collections;

/**
 * Default implementation of {@link org.mytraffic.priv.repositories.TrafficIncidentRepository}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class FavoriteRouteRepositoryImpl extends AbstractJongoRepository<FavoriteRoute> implements
        FavoriteRouteRepository {

    private static final String KEY_FIND_BY_USER_ID = "favoriteRoute.findByUserId";
    private static final String KEY_FIND_NOTIFICATION_TIME_RANGE = "favoriteRoute.findByNotificationTimeRange";
    private static final String KEY_FIND_FROM_NOTIFICATION_TIME = "favoriteRoute.findByNotificationTimeRange";
    private static final String KEY_FIND_TO_NOTIFICATION_TIME = "favoriteRoute.findByNotificationTimeRange";

    @Override
    public Iterable<FavoriteRoute> findByUserId(String userId) throws MongoDataException {
        return find(getQueryFor(KEY_FIND_BY_USER_ID), userId);
    }

    @Override
    public Iterable<FavoriteRoute> findByNotificationTimeRange(LocalTime from, LocalTime to) throws MongoDataException {
        if (from != null && to != null) {
            return find(getQueryFor(KEY_FIND_NOTIFICATION_TIME_RANGE), from.toNanoOfDay(), to.toNanoOfDay());
        } else if (from != null) {
            return find(getQueryFor(KEY_FIND_FROM_NOTIFICATION_TIME), from.toNanoOfDay());
        } else if (to != null) {
            return find(getQueryFor(KEY_FIND_TO_NOTIFICATION_TIME), to.toNanoOfDay());
        } else {
            return Collections.emptyList();
        }
    }

}
