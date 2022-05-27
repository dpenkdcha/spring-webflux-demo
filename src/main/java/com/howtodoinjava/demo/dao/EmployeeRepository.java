package com.howtodoinjava.demo.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.howtodoinjava.demo.model.Employee;

import reactor.core.publisher.Flux;

//@Repository
//public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {
//    //@Query("{ 'name': ?0 }")
//    Flux<Employee> findByName(final String name);
//}

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {
	
	@Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);
}