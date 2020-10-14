/*! \file DependentDao.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Repository for Dependent information
*/

package com.xerus.records.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xerus.records.entity.Dependent;

@Repository
public interface DependentDao extends MongoRepository<Dependent, String> {
}
