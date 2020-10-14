/*! \file EnrolleeDao.java
    \date 10/14/2020
    \author Raymond Moorhead
    \brief Repository for Enrollee information
*/

package com.xerus.records.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xerus.records.entity.Enrollee;

@Repository
public interface EnrolleeDao extends MongoRepository<Enrollee, String> {
}