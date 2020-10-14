/*! \file Dependent.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Container for Dependent information, such as id, name, etc.
*/

package com.xerus.records.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Dependent {
	@Id
	private String id;
	private String name;
	private Date birthDate;
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
