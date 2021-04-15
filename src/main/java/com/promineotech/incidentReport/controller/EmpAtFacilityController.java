package com.promineotech.incidentReport.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.incidentReport.service.EmpAtFacilityService;



@RestController
@RequestMapping("facility/{id}/employees")
public class EmpAtFacilityController {
	
	@Autowired
	private EmpAtFacilityService service;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> fillFacility(@RequestBody Set<Long> empAtFacilityId, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.submitNewFacility(empAtFacilityId, id), HttpStatus.CREATED );
		} catch (Exception e) {
			return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
		}
	}


}