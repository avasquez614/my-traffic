package org.mytraffic.data.repositories;

import org.craftercms.commons.mongo.CrudRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.TrafficIncident;

import java.time.ZonedDateTime;

/**
 * Mongo repository for {@link org.mytraffic.api.TrafficIncident}s.
 *
 * @author avasquez
 * @author mariobarque
 */
public interface TrafficIncidentRepository extends CrudRepository<TrafficIncident> {

    Iterable<TrafficIncident> findByDateRange(ZonedDateTime from, ZonedDateTime to) throws MongoDataException;

}
