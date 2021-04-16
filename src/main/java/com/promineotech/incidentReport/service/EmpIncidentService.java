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
	private IncidentRepository incidentRepo;
	
	
	public EmpIncident submitNewIncident(Set<Long> employeeIds, Long incidentId) throws Exception{
	try {
		Incident incident = incidentRepo.findOne(incidentId);
		EmpIncident empIncident = initializeNewIncident(employeeIds, incident);
		return repo.save(empIncident);
	} catch (Exception e) {
		logger.error("Exception occurred while trying to create new order for customer: " + incidentId, e);
		throw e;
	}
}

	private EmpIncident initializeNewIncident(Set<Long> employeeIds, Incident incident) {
		EmpIncident empIncident2 = new EmpIncident();
		empIncident2.setEmployee(convertToSet(employeeRepo.findAll(employeeIds)));
		empIncident2.setIncidents(incident);
		return (empIncident2);
	}

	private Set<Employee> convertToSet(Iterable<Employee> iterable) {
		Set<Employee> set = new HashSet<Employee>();
		for (Employee employee : iterable) {
			set.add(employee);
		}
		return set;
	}
}
