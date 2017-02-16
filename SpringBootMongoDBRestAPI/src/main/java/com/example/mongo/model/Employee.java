package com.example.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="EmployeeCollection")
public class Employee {

	@Id
	private int empId;
	private String employeeName;
	private Double employeeSalary;
	private String employeeProfession;

	public Employee() {
	}

	public Employee(int empId, String employeeName, Double employeeSalary, String employeeProfession) {
		this.empId = empId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeProfession = employeeProfession;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Double getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeProfession() {
		return employeeProfession;
	}

	public void setEmployeeProfession(String employeeProfession) {
		this.employeeProfession = employeeProfession;
	}
	
	@Override
	public String toString() {
		return "[" + this.empId
				+ "," + this.employeeName
				+ "," + this.employeeSalary
				+ "," + this.employeeProfession
				+ "]";
	}

}
