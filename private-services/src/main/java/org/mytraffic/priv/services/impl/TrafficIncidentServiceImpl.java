package org.mytraffic.priv.services.impl;

import org.craftercms.commons.collections.IterableUtils;
import org.craftercms.commons.mongo.MongoDataException;
import org.mytraffic.TrafficIncident;
import org.mytraffic.priv.api.exceptions.PrivateApiErrorCode;
import org.mytraffic.priv.api.exceptions.PrivateApiException;
import org.mytraffic.priv.api.services.TrafficIncidentService;
import org.mytraffic.priv.api.utils.DateTimeUtils;
import org.mytraffic.priv.repositories.TrafficIncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Default implementation of {@link org.mytraffic.priv.api.services.TrafficIncidentService}.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class TrafficIncidentServiceImpl implements TrafficIncidentService {

    @Autowired
    private TrafficIncidentRepository repository;

    @Override
    public TrafficIncident findIncidentById(String id) throws PrivateApiException {
        try {
            return repository.findById(id);
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to find incident " +
                    "by ID '" + id + "'", e);
        }
    }

    @Override
    public List<TrafficIncident> findIncidentsByDateRange(ZonedDateTime from, ZonedDateTime to)
            throws PrivateApiException {
        try {
            return IterableUtils.toList(repository.findByDateRange(from, to));
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to find incidents " +
                    "by last modified range [" + DateTimeUtils.formatDateTime(from) + ", " + DateTimeUtils
                    .formatDateTime(to) + "]", e);
        }
    }

    @Override
    public TrafficIncident addIncident(TrafficIncident incident) throws PrivateApiException {
        incident.setId(null);

        try {
            repository.insert(incident);
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to add incident", e);
        }

        return incident;
    }

    @Override
    public TrafficIncident updateIncident(TrafficIncident incident) throws PrivateApiException {
        try {
            if (repository.findById(incident.getId().toString()) != null) {
                repository.save(incident);

                return incident;
            } else {
                throw new PrivateApiException(PrivateApiErrorCode.NO_SUCH_INCIDENT, "Can't update incident '" +
                        incident.getId() + "' because it doesn't exist in the database");
            }
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to update incident '" +
                    incident.getId() + "'", e);
        }
    }

    @Override
    public void removeIncident(String id) throws PrivateApiException {
        try {
            repository.removeById(id);
        } catch (MongoDataException e) {
            throw new PrivateApiException(PrivateApiErrorCode.OTHER, "Error while trying to remove incident '" +
                    id + "'", e);
        }
    }

}
