package com.promineotech.incidentReport.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("facility/{id}/employees")
public class EmployeesAtFacilityController {

	@Autowired
	private EmployeesAtFacilityController service;
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> fillFacility(@RequestBody Set<Long> employeeAtFacilityIds, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.submitNewFacility(employeeAtFacilityIds, id), HttpStatus.CREATED );
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}
	private MultiValueMap<String, String> submitNewFacility(Set<Long> employeeAtFacilityIds, Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

