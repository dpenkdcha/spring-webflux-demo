package com.howtodoinjava.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howtodoinjava.demo.dao.EmployeeRepository;
import com.howtodoinjava.demo.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	public void create(Employee e) {
		System.out.println("Hi " +  e.toString());
		for (int i=0; i<e.getId();i++) {
			Employee ep =  Employee.builder()
					.id(i)
					.name("user " + i)
					.salary(i*100)
					.build();
			employeeRepo.save(ep).subscribe();
		}
		//employeeRepo.save(e).subscribe();
	}

	public Mono<Employee> findById(Integer id) {
		return employeeRepo.findById(id);
	}

	public Flux<Employee> findByName(String name) {
		return employeeRepo.findByName(name);
	}

	public Flux<Employee> findAll() {
		return employeeRepo.findAll();
	}

	public Mono<Employee> update(Employee e) {
		return employeeRepo.save(e);
	}

	public Mono<Void> delete(Integer id) {
		return employeeRepo.deleteById(id);
	}

}