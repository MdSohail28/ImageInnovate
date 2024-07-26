package com.innovate.ImagiInnovate.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innovate.ImagiInnovate.Entity.Employee;
import com.innovate.ImagiInnovate.Service.EmployeeService;
import com.innovate.ImagiInnovate.vo.EmployeeTaxResponse;

@RestController
@RequestMapping("/api/employees")
public class MainRestController {
	
	@Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee emp = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
    
    @GetMapping("/employee-tax")
    public EmployeeTaxResponse getEmployeeTax(
            @RequestParam String employeeCode,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam double monthlySalary,
            @RequestParam String dateOfJoining
    ) {
        return employeeService.calculateEmployeeTax(employeeCode, firstName, lastName, monthlySalary, dateOfJoining);
    }

}
