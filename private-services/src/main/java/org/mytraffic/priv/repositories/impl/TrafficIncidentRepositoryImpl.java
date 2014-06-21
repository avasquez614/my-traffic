package org.mytraffic.priv.repositories.impl;

import org.craftercms.commons.mongo.AbstractJongoRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.repositories.TrafficIncidentRepository;

import java.time.ZonedDateTime;
import java.util.Collections;

/**
 * Default implementation of {@link org.mytraffic.priv.repositories.TrafficIncidentRepository}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncidentRepositoryImpl extends AbstractJongoRepository<TrafficIncident> implements
        TrafficIncidentRepository {

    private static final String KEY_FIND_BY_DATE_RANGE = "trafficIncidents.findByDateRange";
    private static final String KEY_FIND_FROM_DATE = "trafficIncidents.findFromDate";
    private static final String KEY_FIND_TO_DATE = "trafficIncidents.findToDate";

    @Override
    public Iterable<TrafficIncident> findByDateRange(ZonedDateTime from, ZonedDateTime to) throws MongoDataException {
        if (from != null && to != null) {
            return find(getQueryFor(KEY_FIND_BY_DATE_RANGE), from.toInstant().toEpochMilli(), to.toInstant().toEpochMilli());
        } else if (from != null) {
            return find(getQueryFor(KEY_FIND_FROM_DATE), from.toInstant().toEpochMilli());
        } else if (to != null) {
            return find(getQueryFor(KEY_FIND_TO_DATE), to.toInstant().toEpochMilli());
        } else {
            return Collections.emptyList();
        }
    }

}
