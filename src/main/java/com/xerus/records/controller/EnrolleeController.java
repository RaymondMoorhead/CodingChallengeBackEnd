/*! \file EnrolleeController.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Contains EnrolleeController class
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
//! APi which enables external use of CRUD operations on Enrollee objects in the database
public class EnrolleeController {
	
	@Autowired
	//! The Service which all operations are funneled through
	private EnrolleeService enService;
	
	@GetMapping(value = "/enrollee/get/{id}")
	/*!
		\brief Acquires an Enrollee with the given id from the database via EnrolleeService
		\param id The id of the Enrollee to get
		\return The Enrollee with the supplied id, or null if none exists
		\sa Enrollee, EnrolleeService
	 */
	public Enrollee getEnrollee(@PathVariable(value = "id") String id) {
		return enService.getEnrollee(id);
	}

	@PutMapping(value = "/enrollee/add")
	/*!
		\brief Adds an Enrollee with the given values to the database via EnrolleeService
		\param enrollee The Enrollee information to add, must have a name and birth date
		\return The Enrollee object added, or null if there was missing information
		\sa Enrollee, EnrolleeService
	 */
	public Enrollee addEnrollee(@RequestBody Enrollee enrollee) {
		return enService.addEnrollee(enrollee);
	}
	
	@PutMapping(value = "/enrollee/update")
	/*!
		\brief Updates an existing Enrollee with the given values in the database via EnrolleeService
		\param enrollee The Enrollee information to update with, including the id used to find the original
		\return True if the update was successful, false otherwise (such as no id, or non-existent id)
		\sa Enrollee, EnrolleeService
	 */
	public boolean updateEnrollee(@RequestBody Enrollee enrollee) {
		return enService.updateEnrollee(enrollee);
	}
	
	@DeleteMapping(value = "/enrollee/delete/{id}")
	/*!
		\brief Deletes the Enrollee with the given id via EnrolleeService, including their Dependents
		\param id The id of the Enrollee to delete
		\return True if an Enrollee of the given id existed, false otherwise
		\sa Enrollee, EnrolleeService
	 */
	public boolean deleteEnrollee(@PathVariable(value = "id") String id) {
		return enService.deleteEnrollee(id);
	}
	
	@PutMapping(value = "/enrollee/add/{enId}/dependent/{depId}")
	/*!
		\brief Adds a Dependent to an Enrollee in the database via EnrolleeService
		\param enId The id of the Enrollee to add the Dependent to
		\param depId The id of the Dependent to add to the Enrollee
		\return True if the addition was successful, false otherwise
		\sa Enrollee, EnrolleeService
	 */
	public boolean addEnrolleeDependent(@PathVariable(value = "enId") String enId,
										@PathVariable(value = "depId") String depId) {
		return enService.addEnrolleeDependent(enId, depId);
	}
	
	@DeleteMapping(value = "/enrollee/delete/{enId}/dependent/{depId}")
	/*!
		\brief Removes a Dependent from an Enrollee in the database via EnrolleeService
		\param enId The id of the Enrollee to remove the Dependent from
		\param depId The id of the Dependent to remove from the Enrollee
		\return True if the rmoval was successful, false otherwise
		\sa Enrollee, EnrolleeService
	 */
	public boolean deleteEnrolleeDependent(@PathVariable(value = "enId") String enId,
											@PathVariable(value = "depId") String depId) {
		return enService.deleteEnrolleeDependent(enId, depId);
	}
}
