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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee_facility")
public class EmpAtFacility {

	private Long id;
	
	private Set<Employee> employee;
	
	@JsonIgnore
	private Facility facilities;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "empAtFacility",
	joinColumns = @JoinColumn(name = "empAtFacilitiesId", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "facilityId", referencedColumnName = "id"))
	//@ManyToMany(mappedBy = "empAtFacility")
	public Facility getFacilities() {
		return facilities;
	}

	public void setFacilities(Facility facilities) {
		this.facilities = facilities;
	}

	@OneToMany(mappedBy = "emp")
	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}


	
}

