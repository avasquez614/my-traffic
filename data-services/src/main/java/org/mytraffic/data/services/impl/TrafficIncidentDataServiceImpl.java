package org.mytraffic.data.services.impl;

import org.craftercms.commons.collections.IterableUtils;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.data.exceptions.DataErrorCode;
import org.mytraffic.api.data.exceptions.DataServiceException;
import org.mytraffic.api.data.services.TrafficIncidentDataService;
import org.mytraffic.data.repositories.TrafficIncidentRepository;
import org.mytraffic.data.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.api.data.services.TrafficIncidentDataService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class TrafficIncidentDataServiceImpl implements TrafficIncidentDataService {

    @Autowired
    private TrafficIncidentRepository repository;

    @Override
    public TrafficIncident findIncidentById(String id) throws DataServiceException {
        try {
            return repository.findById(id);
        } catch (MongoDataException e) {
            throw new DataServiceException(DataErrorCode.OTHER, "Error while trying to find incident " +
                    "by ID '" + id + "'", e);
        }
    }

    @Override
    public List<TrafficIncident> findIncidentsByDateRange(ZonedDateTime from, ZonedDateTime to)
            throws DataServiceException {
        try {
            return IterableUtils.toList(repository.findByDateRange(from, to));
        } catch (MongoDataException e) {
            throw new DataServiceException(DataErrorCode.OTHER, "Error while trying to find incidents " +
                    "by last modified range [" + DateTimeUtils.formatDateTime(from) + ", " + DateTimeUtils
                    .formatDateTime(to) + "]", e);
        }
    }

    @Override
    public TrafficIncident addIncident(TrafficIncident incident) throws DataServiceException {
        incident.setId(null);

        try {
            repository.insert(incident);
        } catch (MongoDataException e) {
            throw new DataServiceException(DataErrorCode.OTHER, "Error while trying to add incident", e);
        }

        return incident;
    }

    @Override
    public TrafficIncident updateIncident(TrafficIncident incident) throws DataServiceException {
        try {
            if (repository.findById(incident.getId().toString()) != null) {
                repository.save(incident);

                return incident;
            } else {
                throw new DataServiceException(DataErrorCode.NO_SUCH_INCIDENT, "Can't update incident '" +
                        incident.getId() + "' because it doesn't exist in the database");
            }
        } catch (MongoDataException e) {
            throw new DataServiceException(DataErrorCode.OTHER, "Error while trying to update incident '" +
                    incident.getId() + "'", e);
        }
    }

    @Override
    public void removeIncident(String id) throws DataServiceException {
        try {
            repository.removeById(id);
        } catch (MongoDataException e) {
            throw new DataServiceException(DataErrorCode.OTHER, "Error while trying to remove incident '" +
                    id + "'", e);
        }
    }

}
