package org.mytraffic.data.repositories;

import org.craftercms.commons.mongo.CrudRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.FavoriteRoute;

import java.time.LocalTime;

/**
 * Mongo repository for {@link org.mytraffic.api.FavoriteRoute}s.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface FavoriteRouteRepository extends CrudRepository<FavoriteRoute> {

    Iterable<FavoriteRoute> findByUserId(String userId) throws MongoDataException;

    Iterable<FavoriteRoute> findByNotificationTimeRange(LocalTime from, LocalTime to) throws MongoDataException;

}
