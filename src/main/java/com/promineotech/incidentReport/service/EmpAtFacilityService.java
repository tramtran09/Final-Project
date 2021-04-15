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
	
	
	public EmpAtFacility submitNewFacility(Set<Long> employeeIds, Long facilityId) throws Exception{
	try {
		Facility facility = facilityRepo.findOne(facilityId);
		EmpAtFacility emp = initializeNewFacility(employeeIds, facility);
		return repo.save(emp);
	} catch (Exception e) {
		logger.error("Exception occurred while trying to create new order for customer: " + facilityId, e);
		throw e;
	}
}

	private EmpAtFacility initializeNewFacility(Set<Long> employeeIds, Facility facility) {
		EmpAtFacility emp2 = new EmpAtFacility();
		emp2.setEmployee(convertToSet(employeeRepo.findAll(employeeIds)));
		emp2.setFacilities(facility);
		//addEmpToFacility(facility);
		return (emp2);
	}

	private Set<Employee> convertToSet(Iterable<Employee> iterable) {
		Set<Employee> set = new HashSet<Employee>();
		for (Employee employee : iterable) {
			set.add(employee);
		}
		return set;
	}

}
