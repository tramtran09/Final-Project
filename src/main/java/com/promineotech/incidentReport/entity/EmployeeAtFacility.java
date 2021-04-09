package com.promineotech.incidentReport.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmployeeAtFacility {

	private Long id;
	
	private Set<Facility> facilities;
	
	@JsonIgnore
	private Employee employees;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//@ManyToMany(mappedBy = "facilities")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "employee_facility",
	joinColumns = @JoinColumn(name = "employeeFacilitiesId", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "facilityId", referencedColumnName = "id"))
	public Set<Facility> getFacilities() {
		return facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

	@ManyToOne
	@JoinColumn(name = "employeeId")
	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}
	
}