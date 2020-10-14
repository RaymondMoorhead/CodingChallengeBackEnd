/*! \file EnrolleeService.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Service which acts as a middleman between controllers and EnrolleeDao
*/

package com.xerus.records.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xerus.records.dao.EnrolleeDao;
import com.xerus.records.entity.Dependent;
import com.xerus.records.entity.Enrollee;

@Service
public class EnrolleeService {

	@Autowired
	private EnrolleeDao enDao;
	
	/*!
		\brief Creates and adds an Enrollee to the EnrolleeDao given basic information (no phone number)
		\param name The name of the Enrollee we wish to create
		\param birthDate The birth date of the Enrollee we wish to create
		\param dependents The dependents of the Enrollee we wish to create
		\return The newly created Enrollee in EnrolleeDao
		\sa Enrollee, EnrolleeDao
	*/
	public Enrollee AddEnrollee(String name, Date birthDate, Dependent... dependents) {
		return AddEnrollee(name, birthDate, "Not Given", dependents);
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
	public Enrollee AddEnrollee(String name, Date birthDate, String phoneNumber, Dependent... dependents) {
		Enrollee enrollee = new Enrollee();
		
		// set the data
		enrollee.setId(null);
		enrollee.setName(name);
		enrollee.setBirthDate(birthDate);
		enrollee.setPhoneNumber(phoneNumber);
		enrollee.setDependents(Arrays.asList(dependents));
		
		return AddEnrollee(enrollee);
	}
	
	/*!
		\brief Adds an Enrollee to the EnrolleeDao given an Enrollee object with no id
		\param enrollee The Enrollee to add
		\return The Enrollee added to EnrolleeDao
		\sa Enrollee, EnrolleeDao
	 */
	public Enrollee AddEnrollee(Enrollee enrollee) {
		if(enrollee.getId() == null)
			return enDao.insert(enrollee);
		return null;
	}
	
	/*!
		\brief Deletes the Enrollee with the given id from EnrolleeDao
		\param id The id of the Enrollee to delete
		\return True if an Enrollee of the given id existed, false otherwise
		\sa Enrollee, EnrolleeDao
	 */
	public boolean DeleteEnrollee(String id) {
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
	public Enrollee GetEnrollee(String id) {
		Optional<Enrollee> result = enDao.findById(id);
		return (result.isPresent() ? result.get() : null);
	}
}
