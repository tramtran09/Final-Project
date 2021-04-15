package com.promineotech.incidentReport.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.incidentReport.entity.EmpIncident;
import com.promineotech.incidentReport.entity.Employee;
import com.promineotech.incidentReport.entity.Incident;
import com.promineotech.incidentReport.repository.EmpIncidentRepository;
import com.promineotech.incidentReport.repository.EmployeeRepository;
import com.promineotech.incidentReport.repository.IncidentRepository;

@Service
public class EmpIncidentService {


	public static final Logger logger = (Logger) LogManager.getLogger(FacilityService.class);
	
	@Autowired
	private EmpIncidentRepository repo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private IncidentRepository facilityRepo;
	
	
	public Incident submitNewIncident(Set<Long> EmpIncidentIds, Long employeeId) throws Exception{
	try {
		Employee employee = employeeRepo.findOne(employeeId);
		Incident facility = initializeNewIncident(EmpIncidentIds, employee);
		return facilityRepo.save(facility);
	} catch (Exception e) {
		logger.error("Exception occurred while trying to create new order for customer: " + employeeId, e);
		throw e;
	}
}

	private Incident initializeNewIncident(Set<Long> EmpIncidentIds, Employee employee) {
		Incident incident = new Incident();
		incident.setEmployeeIncidents(convertToSet(repo.findAll(EmpIncidentIds)));
		incident.setDate(incident.getDate());
		incident.setTime(incident.getTime());
		incident.setLocation(incident.getLocation());
		incident.setIncidentDescription(incident.getIncidentDescription());
		incident.setInjuryDescription(incident.getInjuryDescription());
		incident.setIncidentCategory(incident.getIncidentCategory());
		return (incident);
	}

	private Set<EmpIncident> convertToSet(Iterable<EmpIncident> iterable) {
		Set<EmpIncident> set = new HashSet<EmpIncident>();
		for (EmpIncident empIncident : iterable) {
			set.add(empIncident);
		}
		return set;
	}
}
