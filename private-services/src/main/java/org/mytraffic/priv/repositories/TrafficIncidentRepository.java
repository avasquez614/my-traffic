package org.mytraffic.priv.repositories;

import org.craftercms.commons.mongo.CrudRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.TrafficIncident;

import java.time.ZonedDateTime;

/**
 * Mongo repository for {@link org.mytraffic.TrafficIncident}s.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface TrafficIncidentRepository extends CrudRepository<TrafficIncident> {

    Iterable<TrafficIncident> findByDateRange(ZonedDateTime from, ZonedDateTime to) throws MongoDataException;

}
