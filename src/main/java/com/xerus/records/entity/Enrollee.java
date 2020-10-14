/*! \file Enrollee.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Container for Enrollee information, such as id, name, etc.
*/

package com.xerus.records.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
public class Enrollee {

	@Id
	private String id;
	private String name;
	private boolean activationStatus;
	
	@JsonFormat(pattern="MM-dd-yyyy")
	private Date birthDate;
	
	private String phoneNumber;
	private List<Dependent> dependents;
	
	public Enrollee() {
		super();
		activationStatus = false;
		dependents = new ArrayList<Dependent>();
	}
	
	// GETTERS AND SETTERS
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActivationStatus() {
		return activationStatus;
	}
	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Dependent> getDependents() {
		return dependents;
	}
	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}
}
