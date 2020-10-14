/*! \file EnrolleeController.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Controller which allows access to Enrollees
*/

package com.xerus.records.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.xerus.records.entity.Enrollee;
import com.xerus.records.service.EnrolleeService;

@RestController
public class EnrolleeController {
	
	@Autowired
	private EnrolleeService enService;
	
	@GetMapping(value = "/enrollee/get/{id}")
	public Enrollee getEnrollee(@PathVariable(value = "id") String id) {
		return enService.GetEnrollee(id);
	}

	@PutMapping(value = "/enrollee/add")
	/*!
		\brief Adds an Enrollee with the given values to the database via EnrolleeService
		\param enrollee The Enrollee information to add, must have no id
		\return The Enrollee object added
		\sa Enrollee, EnrolleeService
	 */
	public Enrollee addEnrollee(@RequestBody Enrollee enrollee) {
		return enService.AddEnrollee(enrollee);
	}
	
	@DeleteMapping(value = "/enrollee/delete/{id}")
	/*!
		\brief Deletes the Enrollee with the given id via EnrolleeService
		\param id The id of the Enrollee to delete
		\return True if an Enrollee of the given id existed, false otherwise
		\sa Enrollee, EnrolleeService
	 */
	public boolean deleteEnrollee(@PathVariable(value = "id") String id) {
		return enService.DeleteEnrollee(id);
	}
}
