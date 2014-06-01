package org.mytraffic.services.impl;

import org.mytraffic.api.IncidentSeverity;
import org.mytraffic.api.IncidentType;
import org.mytraffic.api.MapPoint;
import org.mytraffic.api.TrafficIncident;
import org.mytraffic.api.services.TrafficIncidentService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Dummy {@link org.mytraffic.api.services.TrafficIncidentService}, with sample incidents.
 *
 * @author avasquez
 * @author mariobarque
 */
@Service
public class DummyTrafficIncidentService implements TrafficIncidentService {

    private List<TrafficIncident> sampleIncidents;

    @PostConstruct
    public void init() {
        TrafficIncident accident = new TrafficIncident();
        accident.setId(UUID.randomUUID().toString());
        accident.setType(IncidentType.ACCIDENT);
        accident.setSeverity(IncidentSeverity.MINOR);
        accident.setDescription("Choque entre un automóvil y una moto frente al Real Cariari");
        accident.setLocation(new MapPoint(9.982088, -84.159967));
        accident.setStart(new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(2)));
        accident.setLastModified(accident.getStart());

        TrafficIncident construction = new TrafficIncident();
        construction.setId(UUID.randomUUID().toString());
        construction.setType(IncidentType.CONSTRUCTION);
        construction.setSeverity(IncidentSeverity.MODERATE);
        construction.setDescription("Calle en reparación frente al Hospital de Niños");
        construction.setLocation(new MapPoint(9.934389, -84.087535));
        construction.setStart(new Date());
        construction.setLastModified(accident.getStart());

        sampleIncidents = Arrays.asList(accident, construction);
    }

    @Override
    public List<TrafficIncident> findByDateRange(Date from, Date to) {
        List<TrafficIncident> incidentsToReturn = new ArrayList<TrafficIncident>(sampleIncidents);

        if (from != null) {
            for (Iterator<TrafficIncident> iter = incidentsToReturn.iterator(); iter.hasNext();) {
                TrafficIncident incident = iter.next();
                if (incident.getLastModified().before(from)) {
                    iter.remove();
                }
            }
        }
        if (to != null) {
            for (Iterator<TrafficIncident> iter = incidentsToReturn.iterator(); iter.hasNext();) {
                TrafficIncident incident = iter.next();
                if (incident.getLastModified().after(to)) {
                    iter.remove();
                }
            }
        }

        return incidentsToReturn;
    }

}
