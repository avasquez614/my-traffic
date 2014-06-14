package org.mytraffic.data.services.impl;

import org.craftercms.commons.collections.IterableUtils;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.data.exceptions.DataApiErrorCode;
import org.mytraffic.api.data.exceptions.DataApiException;
import org.mytraffic.api.data.services.TrafficIncidentService;
import org.mytraffic.data.repositories.TrafficIncidentRepository;
import org.mytraffic.data.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.api.data.services.TrafficIncidentService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class TrafficIncidentServiceImpl implements TrafficIncidentService {

    @Autowired
    private TrafficIncidentRepository repository;

    @Override
    public TrafficIncident findIncidentById(String id) throws DataApiException {
        try {
            return repository.findById(id);
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to find incident " +
                    "by ID '" + id + "'", e);
        }
    }

    @Override
    public List<TrafficIncident> findIncidentsByDateRange(ZonedDateTime from, ZonedDateTime to)
            throws DataApiException {
        try {
            return IterableUtils.toList(repository.findByDateRange(from, to));
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to find incidents " +
                    "by last modified range [" + DateTimeUtils.formatDateTime(from) + ", " + DateTimeUtils
                    .formatDateTime(to) + "]", e);
        }
    }

    @Override
    public TrafficIncident addIncident(TrafficIncident incident) throws DataApiException {
        incident.setId(null);

        try {
            repository.insert(incident);
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to add incident", e);
        }

        return incident;
    }

    @Override
    public TrafficIncident updateIncident(TrafficIncident incident) throws DataApiException {
        try {
            if (repository.findById(incident.getId().toString()) != null) {
                repository.save(incident);

                return incident;
            } else {
                throw new DataApiException(DataApiErrorCode.NO_SUCH_INCIDENT, "Can't update incident '" +
                        incident.getId() + "' because it doesn't exist in the database");
            }
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to update incident '" +
                    incident.getId() + "'", e);
        }
    }

    @Override
    public void removeIncident(String id) throws DataApiException {
        try {
            repository.removeById(id);
        } catch (MongoDataException e) {
            throw new DataApiException(DataApiErrorCode.OTHER, "Error while trying to remove incident '" +
                    id + "'", e);
        }
    }

}
