package com.example.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer>{

	
	
	
}
