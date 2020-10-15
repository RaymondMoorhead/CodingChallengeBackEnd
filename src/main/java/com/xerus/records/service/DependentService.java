/*! \file DependentService.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Service which acts as a middleman between controllers and DependentDao
*/

package com.xerus.records.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xerus.records.dao.DependentDao;
import com.xerus.records.entity.Dependent;

@Service
public class DependentService {

	@Autowired
	private DependentDao depDao;
	
	/*!
		\brief Adds a Dependent to the DependentDao if they aren't present in it
		\param dependent The dependent we will add
		\return The Dependent generated in DependentDao
		\sa Dependent, DependentDao
	 */
	public Dependent addDependent(Dependent dependent) {
		if((dependent.getName() != null) &&
				(dependent.getBirthDate() != null))
				return depDao.insert(dependent);
			return null;
	}
	
	/*!
	\brief Updates an Dependent in the DependentDao given an Dependent object with a valid id
	\param dependent The Dependent to add
	\return True if the update was successful, false otherwise
	\sa Dependent, DependentDao
 */
	public boolean updateDependent(Dependent dependent) {
		if((dependent.getId() != null) &&
			(depDao.existsById(dependent.getId())))
			return depDao.save(dependent) != null;
		return false;
	}

	/*!
		\brief Deletes the Dependent with the given id from DependentDao
		\param id The id of the Dependent to delete
		\return True if an Dependent of the given id existed, false otherwise
		\sa Dependent, DependentDao
 	*/
	public boolean deleteDependent(String id) {
		if(depDao.existsById(id)) {
			depDao.deleteById(id);
			return true;
		}
		return false;
	}
	
	/*!
		\brief Gets a Dependent from the DependentDao, or null if none exist
		\param id The id of the desired Dependent
		\return The Dependent DependentDao, or null if none is present
		\sa Dependent, DependentDao
	 */
	public Dependent getDependent(String id) {
		Optional<Dependent> dependent = depDao.findById(id);
		if(dependent.isPresent())
			return dependent.get();
		else
			return null;
	}
}
