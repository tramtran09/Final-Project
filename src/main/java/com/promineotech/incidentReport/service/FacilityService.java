package com.promineotech.incidentReport.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.incidentReport.entity.Facility;

import com.promineotech.incidentReport.repository.FacilityRepository;

@Service
public class FacilityService {

	public static final Logger logger = (Logger) LogManager.getLogger(FacilityService.class);
	
	@Autowired
	private FacilityRepository facilityRepo;
	
	

	public Facility getFacilityById(Long id) throws Exception{
		try {
			return facilityRepo.findOne(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to retrieve facility: " + id, e);
			throw e;
		}
	}
	
	public Iterable<Facility>getFacilities(){
		return facilityRepo.findAll();
	}
	
	public Facility createFacility(Facility facility) {
		return facilityRepo.save(facility);
	}
	
	public Facility updateFacility(Facility facility, Long id) throws Exception {
		try {
			Facility addFacility = facilityRepo.findOne(id);
			addFacility.setName(facility.getName());
			addFacility.setAddress(facility.getAddress());
			addFacility.setState(facility.getState());
			addFacility.setCity(facility.getCity());
			addFacility.setZip(facility.getZip());
			addFacility.setEmployee(facility.getEmployee());
			return facilityRepo.save(addFacility);
		} catch (Exception e) {
			logger.error("Exception occurred while trying to add facility: " + id, e);
			throw new Exception("Unable to add facility.");
		}
	}
	
	public void deleteFacility(Long id) throws Exception{
		try {
			facilityRepo.delete(id);
		} catch (Exception e) {
			logger.error("Exception occured while trying to delete employee: " + id, e);
			throw new Exception("Unable to delete.");
		}
	}

	


}
