package com.promineotech.incidentReport.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facility {

	private Long id;
	private String name;
	private String address;
	private String state;
	private String city; 
	private String zip;
	
	private Set<EmployeeAtFacility> employeeAtFacilities;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	public Employee getEmployee() {
		return employees;
	}
	public void setEmployee(Employee employees) {
		this.employees = employees;
	}
	
	@OneToMany(mappedBy = "EmployeeAtFacility")
	public Set<EmployeeAtFacility> getEmployeeAtFacilities() {
		return employeeAtFacilities;
	}
	public void setEmployeeAtFacilities(Set<EmployeeAtFacility> employeeAtFacilities) {
		this.employeeAtFacilities = employeeAtFacilities;
	}
}
