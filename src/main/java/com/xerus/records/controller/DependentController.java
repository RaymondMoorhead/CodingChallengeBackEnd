/*! \file DependentController.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Contains DependentController class
*/

package com.xerus.records.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xerus.records.entity.Dependent;
import com.xerus.records.service.DependentService;

@RestController
//! APi which enables external use of CRUD operations on Dependent objects in the database
public class DependentController {

	@Autowired
	//! The Service which all operations are funneled through
	private DependentService depService;
	
	@GetMapping(value = "/dependent/get/{id}")
	/*!
		\brief Acquires a Dependent with the given id from the database via DependentService
		\param id The id of the Dependent to get
		\return The Dependent with the supplied id, or null if none exists
		\sa Dependent, DependentService
	 */
	public Dependent getDependent(@PathVariable(value = "id") String id) {
		return depService.getDependent(id);
	}

	@PutMapping(value = "/dependent/add")
	/*!
		\brief Adds an Enrollee with the given values to the database via DependentService
		\param dependent The Dependent information to add, must have a name and birth date
		\return The Dependent object added, or null if there was missing information
		\sa Dependent, DependentService
	 */
	public Dependent addDependent(@RequestBody Dependent dependent) {
		return depService.addDependent(dependent);
	}
	
	@PutMapping(value = "/dependent/update")
	/*!
		\brief Updates an existing Dependent with the given values in the database via DependentService
		\param dependent The Dependent information to update with, including the id used to find the original
		\return True if the update was successful, false otherwise (such as no id, or non-existent id)
		\sa Dependent, DependentService
	 */
	public boolean updateDependent(@RequestBody Dependent dependent) {
		return depService.updateDependent(dependent);
	}
	
	@DeleteMapping(value = "/dependent/delete/{id}")
	/*!
		\brief Deletes the Dependent with the given id via DependentService
		\param id The id of the Dependent to delete
		\return True if an Dependent of the given id existed, false otherwise
		\sa Dependent, DependentService
	 */
	public boolean deleteDependent(@PathVariable(value = "id") String id) {
		return depService.deleteDependent(id);
	}
}
