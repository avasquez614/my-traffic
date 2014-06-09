package org.mytraffic.data.repositories.impl;

import org.craftercms.commons.mongo.AbstractJongoRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.FavoriteRoute;
import org.mytraffic.data.repositories.FavoriteRouteRepository;

import java.time.LocalTime;

/**
 * Default implementation of {@link org.mytraffic.data.repositories.TrafficIncidentRepository}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class FavoriteRouteRepositoryImpl extends AbstractJongoRepository<FavoriteRoute> implements
        FavoriteRouteRepository {

    private static final String KEY_FIND_BY_USER_ID =               "favoriteRoute.findByUserId";
    private static final String KEY_FIND_NOTIFICATION_TIME_RANGE =  "favoriteRoute.findByNotificationTimeRange";

    @Override
    public Iterable<FavoriteRoute> findByUserId(String userId) throws MongoDataException {
        return find(getQueryFor(KEY_FIND_BY_USER_ID), userId);
    }

    @Override
    public Iterable<FavoriteRoute> findByNotificationTimeRange(LocalTime from, LocalTime to) throws MongoDataException {
        return find(getQueryFor(KEY_FIND_NOTIFICATION_TIME_RANGE), from.toSecondOfDay(), to.toSecondOfDay());
    }

}
