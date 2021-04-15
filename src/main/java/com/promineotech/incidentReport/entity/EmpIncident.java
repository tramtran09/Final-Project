package com.promineotech.incidentReport.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee_incidents")
public class EmpIncident {

	private Long id;
	
	private Set<Incident> incidents;
	
	@JsonIgnore
	private Employee employee;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "empIncidents",
	joinColumns = @JoinColumn(name = "empIncidentsId", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "incidentsId", referencedColumnName = "id"))
	public Set<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(Set<Incident> incidents) {
		this.incidents = incidents;
	}

	@ManyToOne
	@JoinColumn(name = "employeeId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
