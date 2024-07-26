package com.innovate.ImagiInnovate.vo;

public class EmployeeTaxResponse {
	
	private String employeeCode;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;
    
    

    public String getEmployeeCode() {
		return employeeCode;
	}



	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public double getYearlySalary() {
		return yearlySalary;
	}



	public void setYearlySalary(double yearlySalary) {
		this.yearlySalary = yearlySalary;
	}



	public double getTaxAmount() {
		return taxAmount;
	}



	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}



	public double getCessAmount() {
		return cessAmount;
	}



	public void setCessAmount(double cessAmount) {
		this.cessAmount = cessAmount;
	}



	public EmployeeTaxResponse(String employeeCode, String firstName, String lastName, double yearlySalary, double taxAmount, double cessAmount) {
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearlySalary = yearlySalary;
        this.taxAmount = taxAmount;
        this.cessAmount = cessAmount;
    }



	public EmployeeTaxResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "EmployeeTaxResponse [employeeCode=" + employeeCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", yearlySalary=" + yearlySalary + ", taxAmount=" + taxAmount + ", cessAmount="
				+ cessAmount + "]";
	}
	
	

}
