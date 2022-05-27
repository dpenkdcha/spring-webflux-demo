package com.howtodoinjava.demo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document("emps")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
 
    @Id
    int id;
    
    @Field("emp_name")
    String name;
    long salary;
 
    //Getters and setters
    
    
 
//    @Override
//    public String toString() {
//        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
//    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
}
