package org.appengine.service.utils;

import org.appengine.service.EmployeeService;
import org.appengine.service.impl.EmployeeServiceImpl;

public class ServiceUtils {

	public static EmployeeService getEmployeeService() {
		return EmployeeServiceImpl.getInstance();
	}

}
