package org.mytraffic.priv.repositories.impl;

import org.craftercms.commons.mongo.AbstractJongoRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.repositories.TrafficIncidentRepository;

import java.time.ZonedDateTime;

/**
 * Default implementation of {@link org.mytraffic.priv.repositories.TrafficIncidentRepository}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncidentRepositoryImpl extends AbstractJongoRepository<TrafficIncident> implements
        TrafficIncidentRepository {

    private static final String KEY_FIND_BY_DATE_RANGE = "trafficIncidents.findByDateRange";

    @Override
    public Iterable<TrafficIncident> findByDateRange(ZonedDateTime from, ZonedDateTime to) throws MongoDataException {
        return find(getQueryFor(KEY_FIND_BY_DATE_RANGE), from.toInstant().toEpochMilli(), to.toInstant().toEpochMilli());
    }

}
