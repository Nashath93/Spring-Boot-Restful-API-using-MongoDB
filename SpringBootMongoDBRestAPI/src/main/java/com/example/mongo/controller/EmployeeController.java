package com.example.mongo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.model.Employee;
import com.example.mongo.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> createEmployee(@RequestBody Employee employee) {

		Map<String, Object> response = new HashMap<String, Object>();

		boolean isEmployeeExist = employeeRepository.exists(employee.getEmpId());

		if (isEmployeeExist == true) {
			System.out.println("The employee " + employee.getEmployeeName() + ", already exist");
			response.put("message", "employee with Id " + employee.getEmpId() + ", already exist");
			return response;
		}

		Employee addedEmployee = employeeRepository.save(employee);
		System.out.println("The employee " + addedEmployee.getEmployeeName() + ", added successfully");
		response.put("message", "Employee " + addedEmployee.getEmployeeName() + ", added successfully");
		response.put("Employee", addedEmployee);
		return response;
	}

	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllEmployees(){
		
		Map<String, Object> response = new HashMap<String,Object>();
		
		List<Employee> listOfEmployees = employeeRepository.findAll();
		if(listOfEmployees.isEmpty()){
			System.out.println("Employees not found in the database");
			response.put("message", "Database does not have employees");
		}
		
		response.put("message", "list of employees found successfully");
		response.put("Employees", listOfEmployees);
		
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getEmployeeById(@PathVariable("id") int id) {

		Map<String, Object> response = new HashMap<String,Object>();
		
		Employee employee = employeeRepository.findOne(id);
		if (employee == null) {
			System.out.println("Employee with Id " + id + ", Not Found");
			response.put("message", "Employee with Id " + id + ", not found");
			return response;
		}
		System.out.println("Employee found: " + employee.toString());
		response.put("message", "employee found successfully");
		response.put("employee", employee);
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@ResponseBody
	public Map<String, Object> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		
		Map<String, Object> response = new HashMap<String,Object>();
		
		Employee currentEmployee = employeeRepository.findOne(id);
		if (currentEmployee == null) {
			System.out.println("The employee with id " + id + ", Not Found");
			response.put("message", "Employee with Id " + id + ", not found");
			return response;
		}

		currentEmployee.setEmployeeName(employee.getEmployeeName());
		currentEmployee.setEmployeeSalary(employee.getEmployeeSalary());
		currentEmployee.setEmployeeProfession(employee.getEmployeeProfession());
		employeeRepository.save(currentEmployee);
		
		response.put("message", "employee updated successfully");
		response.put("employee", currentEmployee);
		return response;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteEmployee(@PathVariable("id") int id) {

		Map<String, Object> response = new HashMap<String, Object>();

		Employee deleteEmployee = employeeRepository.findOne(id);
		if (deleteEmployee == null) {
			System.out.println("The employee with id " + id + ", Not Found");
			response.put("message", "delete failed. Employee with Id " + id + ", not found");
			return response;
		}

		employeeRepository.delete(deleteEmployee);
		response.put("message", "delete success. Employee with Id " + id + ", deleted successfully");
		response.put("Employee", deleteEmployee);

		return response;

	}

}
