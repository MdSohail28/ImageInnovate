package com.innovate.ImagiInnovate.ServiceImpl;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innovate.ImagiInnovate.Entity.Employee;
import com.innovate.ImagiInnovate.Repository.EmployeeRepo;
import com.innovate.ImagiInnovate.Service.EmployeeService;
import com.innovate.ImagiInnovate.vo.EmployeeTaxResponse;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
    private EmployeeRepo employeeRepository;

	public Employee saveEmployee(Employee employee) throws IllegalArgumentException {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    private void validateEmployee(Employee employee) throws IllegalArgumentException {
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty() || employee.getFirstName().length() > 20) {
            throw new IllegalArgumentException("Invalid first name");
        }

        if (employee.getLastName() == null || employee.getLastName().isEmpty() || employee.getLastName().length() > 20) {
            throw new IllegalArgumentException("Invalid last name");
        }

        if (employee.getEmail() == null || !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        if (employee.getPhoneNumber() == null || !employee.getPhoneNumber().matches("^\\+?[0-9]{7,15}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        if (employee.getDoj() == null) {
            throw new IllegalArgumentException("Invalid date of joining");
        }

        if (employee.getSalary() == null || employee.getSalary() < 0) {
            throw new IllegalArgumentException("Invalid salary");
        }
    }

	@Override
	public EmployeeTaxResponse calculateEmployeeTax(String employeeCode, String firstName, String lastName,
			double monthlySalary, String dateOfJoining) {

		LocalDate doj = LocalDate.parse(dateOfJoining);
        LocalDate startOfFinancialYear = LocalDate.of(doj.getYear(), Month.APRIL, 1);
        LocalDate endOfFinancialYear = LocalDate.of(doj.getYear() + 1, Month.MARCH, 31);

        if (doj.isBefore(startOfFinancialYear)) {
            doj = startOfFinancialYear;
        }

        long monthsWorked = ChronoUnit.MONTHS.between(doj, endOfFinancialYear) + 1;
        double totalSalary = monthlySalary * monthsWorked;

        long daysWorkedInStartMonth = ChronoUnit.DAYS.between(doj, doj.withDayOfMonth(doj.lengthOfMonth())) + 1;
        double lossOfPayInStartMonth = (monthlySalary / 30) * (30 - daysWorkedInStartMonth);
        totalSalary -= lossOfPayInStartMonth;

        double taxAmount = calculateTax(totalSalary);
        double cessAmount = calculateCess(totalSalary);

        return new EmployeeTaxResponse(employeeCode, firstName, lastName, totalSalary, taxAmount, cessAmount);
    
	}
	
	private double calculateTax(double salary) {
        double tax = 0;
        if (salary <= 250000) {
            return tax;
        }
        if (salary > 250000 && salary <= 500000) {
            tax += (salary - 250000) * 0.05;
        } else if (salary > 500000 && salary <= 1000000) {
            tax += 250000 * 0.05;
            tax += (salary - 500000) * 0.10;
        } else if (salary > 1000000) {
            tax += 250000 * 0.05;
            tax += 500000 * 0.10;
            tax += (salary - 1000000) * 0.20;
        }
        return tax;
    }

    private double calculateCess(double salary) {
        double cess = 0;
        if (salary > 2500000) {
            cess = (salary - 2500000) * 0.02;
        }
        return cess;
    }


}
