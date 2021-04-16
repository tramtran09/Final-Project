package com.promineotech.incidentReport.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee_incidents")
public class EmpIncident {

	private Long id;
	
	private Set<Employee> employee;
	
	@JsonIgnore
	private Incident incidents;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "empIncident",
	joinColumns = @JoinColumn(name = "empIncidentsId", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "incidentsId", referencedColumnName = "id"))
	public Incident getIncidents() {
		return incidents;
	}

	public void setIncidents(Incident incidents) {
		this.incidents = incidents;
	}

	@OneToMany(mappedBy = "empIncident")
	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
}
