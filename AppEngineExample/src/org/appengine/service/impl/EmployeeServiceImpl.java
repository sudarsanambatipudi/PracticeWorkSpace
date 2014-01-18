package org.appengine.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.appengine.domain.Employee;
import org.appengine.service.EmployeeService;
import org.appengine.service.JPAService;

public class EmployeeServiceImpl implements EmployeeService {

	private static final EmployeeServiceImpl EMPLOYEE_SERIVICE_IMPL = new EmployeeServiceImpl();

	private EmployeeServiceImpl() {

	}

	public static EmployeeService getInstance() {
		return EMPLOYEE_SERIVICE_IMPL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.appengine.service.EmployeeService#create(org.appengine.domain.Employee
	 * )
	 */
	public void create(Employee employee) {
		EntityManager manager = JPAService.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			manager.persist(employee);
			transaction.commit();
		} catch (Exception exception) {

		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			manager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll() {
		EntityManager manager = JPAService.getEntityManager();
		List<Employee> list = null;
		try {
			Query query = manager.createNamedQuery("Employee.findAll");
			list = query.getResultList();

		} catch (Exception exception) {

		} finally {
			manager.close();
		}
		return list;
	}

	@Override
	public Employee findByid(Long id) {
		Employee employee = null;
		EntityManager manager = JPAService.getEntityManager();
		try {
			employee = manager.find(Employee.class, id);
		} catch (Exception exception) {

		} finally {
			manager.close();
		}
		return employee;
	}

	@Override
	public Employee delete(Long id) {
		Employee employee = null;
		EntityManager manager = JPAService.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			employee = manager.find(Employee.class, id);
			if (employee != null) {
				transaction.begin();
				manager.remove(employee);
				transaction.commit();
			}
		} catch (Exception exception) {

		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			manager.close();
		}
		return employee;
	}

	@Override
	public Employee update(Employee employee) {
		Employee employeeObj = null;
		EntityManager manager = JPAService.getEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			employeeObj = manager.find(Employee.class, employee.getId());
			if (employeeObj != null) {
				employeeObj.setId(employee.getId());
				employeeObj.setName(employee.getName());
				employeeObj.setSalary(employee.getSalary());
				transaction.begin();
				manager.merge(employeeObj);
				transaction.commit();
			}
		} catch (Exception exception) {

		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			manager.close();
		}
		return employeeObj;
	}
}
