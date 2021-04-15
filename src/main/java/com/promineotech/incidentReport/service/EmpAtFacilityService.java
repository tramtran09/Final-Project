package com.promineotech.incidentReport.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.incidentReport.entity.EmpAtFacility;
import com.promineotech.incidentReport.entity.Employee;
import com.promineotech.incidentReport.entity.Facility;
import com.promineotech.incidentReport.repository.EmpAtFacilityRepository;
import com.promineotech.incidentReport.repository.EmployeeRepository;
import com.promineotech.incidentReport.repository.FacilityRepository;


@Service
public class EmpAtFacilityService {


	public static final Logger logger = (Logger) LogManager.getLogger(FacilityService.class);
	
	@Autowired
	private EmpAtFacilityRepository repo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private FacilityRepository facilityRepo;
	
	
	public Facility submitNewFacility(Set<Long> EmpAtFacilityIds, Long employeeId) throws Exception{
	try {
		Employee employee = employeeRepo.findOne(employeeId);
		Facility facility = initializeNewFacility(EmpAtFacilityIds, employee);
		return facilityRepo.save(facility);
	} catch (Exception e) {
		logger.error("Exception occurred while trying to create new order for customer: " + employeeId, e);
		throw e;
	}
}

	private Facility initializeNewFacility(Set<Long> EmpAtFacilityIds, Employee employee) {
		Facility facility = new Facility();
		facility.setEmployee(employee);
		facility.setEmpAtFacility(convertToSet(repo.findAll(EmpAtFacilityIds)));
		facility.setAddress(facility.getAddress());
		facility.setState(facility.getState());
		facility.setCity(facility.getCity());
		facility.setZip(facility.getZip());
		//addEmpToFacility(facility);
		return (facility);
	}

	private Set<EmpAtFacility> convertToSet(Iterable<EmpAtFacility> iterable) {
		Set<EmpAtFacility> set = new HashSet<EmpAtFacility>();
		for (EmpAtFacility empAtFacility : iterable) {
			set.add(empAtFacility);
		}
		return set;
	}

}
