package com.promineotech.incidentReport.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.incidentReport.entity.Incident;
import com.promineotech.incidentReport.repository.IncidentRepository;

@Service
public class IncidentService {

	public static final Logger logger = (Logger) LogManager.getLogger(IncidentService.class);

	@Autowired
	private IncidentRepository repo;
	
	public Incident getIncidentById(Long id) throws Exception{
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to retrieve incident: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Incident> getIncident(){
		return repo.findAll();		
	}
	
	public Incident createIncident(Incident incidents) {
		return repo.save(incidents);
	}
	
	public Incident updateIncident(Incident incident, Long id) throws Exception {
		try {
			Incident updateInc = repo.findOne(id);
			updateInc.setDate(incident.getDate());
			updateInc.setTime(incident.getTime());
			updateInc.setLocation(incident.getLocation());
			updateInc.setIncidentDescription(incident.getIncidentDescription());
			updateInc.setInjuryDescription(incident.getInjuryDescription());
			//updateInc.setIncidentCategory(incident.getIncidentCategory());
			return repo.save(updateInc);
		} catch (Exception e) {
			logger.error("Exception occured while trying to add incident: " + id, e);
			throw new Exception("Unable to add incident.");
		}
	}

	public void deleteIncident(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete incident: " + id, e);
			throw new Exception ("Unable to delete.");
		}
	}
}
