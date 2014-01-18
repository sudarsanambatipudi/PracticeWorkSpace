package org.appengine.service;

import java.util.List;

import org.appengine.domain.Employee;

public interface EmployeeService {

	void create(Employee employee);
	
	List<Employee> getAll();
	
	Employee findByid(Long id);
	
	Employee delete(Long id);
	
	Employee update(Employee employee);

}