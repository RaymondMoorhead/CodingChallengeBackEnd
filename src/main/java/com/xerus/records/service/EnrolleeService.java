/*! \file EnrolleeService.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Service which acts as a middleman between controllers and EnrolleeDao
*/

package com.xerus.records.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xerus.records.dao.EnrolleeDao;
import com.xerus.records.entity.Dependent;
import com.xerus.records.entity.Enrollee;

@Service
public class EnrolleeService {

	@Autowired
	//! Enrollee's data access object
	private EnrolleeDao enDao;
	
	@Autowired
	//! Dependent's Service
	private DependentService depService;
	
	/*!
		\brief Creates and adds an Enrollee to the EnrolleeDao given basic information (no phone number)
		\param name The name of the Enrollee we wish to create
		\param birthDate The birth date of the Enrollee we wish to create
		\param dependents The dependents of the Enrollee we wish to create
		\return The newly created Enrollee in EnrolleeDao
		\sa Enrollee, EnrolleeDao
	*/
	public Enrollee addEnrollee(String name, Date birthDate, Dependent... dependents) {
		return addEnrollee(name, birthDate, "Not Given", dependents);
	}
	
	/*!
		\brief Creates and adds an Enrollee to the EnrolleeDao given all information
		\param name The name of the Enrollee we wish to create
		\param birthDate The birth date of the Enrollee we wish to create
		\param phoneNumber The phone number of the Enrollee we wish to create
		\param dependents The dependents of the Enrollee we wish to create
		\return The newly created Enrollee in EnrolleeDao
		\sa Enrollee, EnrolleeDao
	*/
	public Enrollee addEnrollee(String name, Date birthDate, String phoneNumber, Dependent... dependents) {
		Enrollee enrollee = new Enrollee();
		
		// set the data
		enrollee.setId(null);
		enrollee.setName(name);
		enrollee.setBirthDate(birthDate);
		enrollee.setPhoneNumber(phoneNumber);
		enrollee.setDependents(new HashSet<Dependent>(Arrays.asList(dependents)));
		
		return addEnrollee(enrollee);
	}
	
	/*!
		\brief Adds an Enrollee to the EnrolleeDao given an Enrollee object with no id
		\param enrollee The Enrollee to add
		\return The Enrollee added to EnrolleeDao, or null if there was mising information
		\sa Enrollee, EnrolleeDao
	 */
	public Enrollee addEnrollee(Enrollee enrollee) {
		if((enrollee.getName() != null) &&
			(enrollee.getBirthDate() != null))
			return enDao.insert(enrollee);
		return null;
	}
	
	/*!
		\brief Updates an Enrollee in the EnrolleeDao given an Enrollee object with a valid id
		\param enrollee The Enrollee to add
		\return True if the update was successful, false otherwise
		\sa Enrollee, EnrolleeDao
	 */
	public boolean updateEnrollee(Enrollee enrollee) {
		if((enrollee.getId() != null) &&
			(enDao.existsById(enrollee.getId())))
			return enDao.save(enrollee) != null;
		return false;
	}
	
	/*!
		\brief Deletes the Enrollee with the given id from EnrolleeDao
		\param id The id of the Enrollee to delete
		\return True if an Enrollee of the given id existed, false otherwise
		\sa Enrollee, EnrolleeDao
	 */
	public boolean deleteEnrollee(String id) {
		if(enDao.existsById(id)) {
			enDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	/*!
		\brief Gets an Enrollee from EnrolleeDao with an id
		\param id The id of the Enrollee we wish to get
		\return The Enrollee in EnrolleeDao, or null if it doesn't exist
		\sa Enrollee, EnrolleeDao
	*/
	public Enrollee getEnrollee(String id) {
		Optional<Enrollee> result = enDao.findById(id);
		return (result.isPresent() ? result.get() : null);
	}
	
	/*!
		\brief Adds a Dependent to an Enrollee in the database via EnrolleeDao and DependentService
		\param enId The id of the Enrollee to add the Dependent to
		\param depId The id of the Dependent to add to the Enrollee
		\return True if the addition was successful, false otherwise
		\sa Enrollee, EnrolleeDao
	 */
	public boolean addEnrolleeDependent(String enId, String depId) {
		Enrollee enrollee = getEnrollee(enId);
		Dependent dependent = depService.getDependent(depId);
		if(enrollee != null && dependent != null) {
			enrollee.addDependent(dependent);
			return updateEnrollee(enrollee);
		}
		return false;
	}
	
	/*!
		\brief Removes a Dependent from an Enrollee in the database via EnrolleeDao
		\param enId The id of the Enrollee to remove the Dependent from
		\param depId The id of the Dependent to remove from the Enrollee
		\return True if the rmoval was successful, false otherwise
		\sa Enrollee, EnrolleeService
	 */
	public boolean deleteEnrolleeDependent(String enId, String depId) {
		Enrollee enrollee = getEnrollee(enId);
		if(enrollee != null) {
			if(enrollee.deleteDependentById(depId))
				return updateEnrollee(enrollee);
		}
		return false;
	}
}
