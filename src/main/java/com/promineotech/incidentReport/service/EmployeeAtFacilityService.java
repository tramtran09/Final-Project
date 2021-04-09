package com.promineotech.incidentReport.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.promineotech.incidentReport.entity.Employee;
import com.promineotech.incidentReport.entity.EmployeeAtFacility;
import com.promineotech.incidentReport.entity.Facility;
import com.promineotech.incidentReport.repository.EmployeeAtFacilityRepository;
import com.promineotech.incidentReport.repository.EmployeeRepository;
import com.promineotech.incidentReport.repository.FacilityRepository;

public class EmployeeAtFacilityService {


	public static final Logger logger = (Logger) LogManager.getLogger(FacilityService.class);
	
	@Autowired
	private FacilityRepository facilityRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private EmployeeAtFacilityRepository repo;
	
	
	public Facility submitNewFacility(Set<Long> FacilityEmployeeIds, Long employeeId) throws Exception{
		try {
			Employee employee = employeeRepo.findOne(employeeId);
			Facility facility = initializeNewFacility(FacilityEmployeeIds, employee);
			return facilityRepo.save(facility);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to create new order for customer: " + employeeId, e);
			throw e;
		}
	}
	
	private Facility initializeNewFacility(Set<Long> FacilityEmployeeIds, Employee employee) {
		Facility facility = new Facility();
		facility.setEmployee(employee);
		facility.setEmployeeAtFacilities(convertToSet(repo.findAll(FacilityEmployeeIds)));
		return null;
	}
	private Set<EmployeeAtFacility> convertToSet(Iterable<EmployeeAtFacility> iterable) {
		Set<EmployeeAtFacility> set = new HashSet<EmployeeAtFacility>();
		for (EmployeeAtFacility empAtFacility : iterable) {
			set.add(empAtFacility);
		}
		return set;
	}
}
