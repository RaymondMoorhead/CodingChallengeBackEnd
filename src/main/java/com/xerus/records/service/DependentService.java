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
		\return The Dependent in DependentDao
		\sa Dependent, DependentDao
	 */
	public Dependent AddDependentIfMissing(Dependent dependent) {
		return depDao.save(dependent);
	}
	
	/*!
	\brief Gets a Dependent from the DependentDao, or null if none exist
	\param id The id of the desired Dependent
	\return The Dependent DependentDao, or null if none is present
	\sa Dependent, DependentDao
 */
	public Dependent GetDependent(String id) {
		Optional<Dependent> dependent = depDao.findById(id);
		if(dependent.isPresent())
			return dependent.get();
		else
			return null;
	}
}
