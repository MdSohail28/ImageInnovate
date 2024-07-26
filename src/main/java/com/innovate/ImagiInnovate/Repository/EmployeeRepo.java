package com.innovate.ImagiInnovate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovate.ImagiInnovate.Entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	

}
