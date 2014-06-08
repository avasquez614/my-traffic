package org.mytraffic.data.repositories.impl;

import org.craftercms.commons.mongo.AbstractJongoRepository;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.TrafficIncident;
import org.mytraffic.data.repositories.TrafficIncidentRepository;

import java.util.Date;

/**
 * Default implementation of {@link org.mytraffic.data.repositories.TrafficIncidentRepository}.
 *
 * @author avasquez
 * @author mariobarque
 */
public class TrafficIncidentRepositoryImpl extends AbstractJongoRepository<TrafficIncident> implements
        TrafficIncidentRepository {

    private static final String KEY_FIND_BY_DATE_RANGE = "trafficIncidents.findByDateRange";

    @Override
    public Iterable<TrafficIncident> findByDateRange(Date from, Date to) throws MongoDataException {
        return find(getQueryFor(KEY_FIND_BY_DATE_RANGE), from, to);
    }

}
