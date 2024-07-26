package com.innovate.ImagiInnovate.Service;

import com.innovate.ImagiInnovate.Entity.Employee;
import com.innovate.ImagiInnovate.vo.EmployeeTaxResponse;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	
	EmployeeTaxResponse calculateEmployeeTax(String employeeCode, String firstName, String lastName, double monthlySalary, String dateOfJoining);

}
