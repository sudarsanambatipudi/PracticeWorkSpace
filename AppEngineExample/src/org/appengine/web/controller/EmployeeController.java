package org.appengine.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.appengine.domain.Employee;
import org.appengine.service.EmployeeService;
import org.appengine.service.utils.ServiceUtils;

@SuppressWarnings("serial")
public class EmployeeController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("Edit".equalsIgnoreCase(action)) {
			Long id = Long.parseLong(request.getParameter("id"));
			EmployeeService employeeService = ServiceUtils.getEmployeeService();
			Employee employee = employeeService.findByid(id);

			if (employee == null) {
				request.setAttribute("msg", "Record Not Found to Edit");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/employeeList.jsp");
				dispatcher.forward(request, response);
				return;
			}

			request.setAttribute("employee", employee);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/createEmployee.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if ("Delete".equalsIgnoreCase(action)) {
			Long id = Long.parseLong(request.getParameter("id"));
			EmployeeService employeeService = ServiceUtils.getEmployeeService();
			Employee employee = employeeService.delete(id);
			if (employee != null) {
				request.setAttribute("msg", "Record Deleted");
			}

			List<Employee> list = employeeService.getAll();
			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/employeeList.jsp");
			dispatcher.forward(request, response);
			return;
		}

		if ("List".equalsIgnoreCase(action)) {
			EmployeeService employeeService = ServiceUtils.getEmployeeService();
			List<Employee> list = employeeService.getAll();

			request.setAttribute("list", list);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/employeeList.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long employeeId = Long.parseLong(request.getParameter("employeeId"));
		String employeeName = request.getParameter("employeeName");
		Double salary = Double.parseDouble(request
				.getParameter("employeeSalary"));
		String action = request.getParameter("action");

		if ("Update".equalsIgnoreCase(action)) {
			Employee employee = new Employee();
			employee.setId(employeeId);
			employee.setName(employeeName);
			employee.setSalary(salary);

			EmployeeService employeeService = ServiceUtils.getEmployeeService();
			Employee employeeObj = employeeService.update(employee);

			if (employeeObj == null) {
				request.setAttribute("msg", "Record Not Found to Update");
			}

			List<Employee> list = employeeService.getAll();
			request.setAttribute("list", list);
			request.setAttribute("msg", "Record Updated !");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/employeeList.jsp");
			dispatcher.forward(request, response);
			return;

		}

		if ("Create".equalsIgnoreCase(action)) {
			try {

				Employee employee = new Employee();
				employee.setId(employeeId);
				employee.setName(employeeName);
				employee.setSalary(salary);

				EmployeeService employeeService = ServiceUtils
						.getEmployeeService();
				employeeService.create(employee);

				request.setAttribute("msg", "Employee Saved !");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/createEmployee.jsp");
				dispatcher.forward(request, response);

			} catch (Exception exception) {
			}
			return;
		}

	}

}
