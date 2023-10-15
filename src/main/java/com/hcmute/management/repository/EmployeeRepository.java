package com.hcmute.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcmute.management.collection.EmployeeCollection;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeCollection, String> {
    EmployeeCollection findByUsername(String username);
}
