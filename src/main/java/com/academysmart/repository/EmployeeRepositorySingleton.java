package com.academysmart.repository;

import java.util.ArrayList;
import java.util.List;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;

public class EmployeeRepositorySingleton {

	private static EmployeeRepositorySingleton instance;
	private List<Employee> employees = new ArrayList<Employee>();


	public static EmployeeRepositorySingleton getRepository() {
		// TODO implement method that returns repository instance
		if (instance == null) {
			instance = new EmployeeRepositorySingleton();

		}
		return instance;
	}

	int id = 1;

	public void addEmployee(String fname, String lname, String email)
			throws IncorrectEmailException, ServletException {
		// TODO implement method that adds an employee to repository
		// This method should check that email is not used by other employees
		Employee employee = new Employee();
		if (fname.equals("") || lname.equals("") || email.equals("")) {
			throw new ServletException("Fill in all the fields");
		}
		employee.setId(String.valueOf(id));
		employee.setName(fname);
		employee.setSurname(lname);
		employee.setEmail(email);
		for (Employee i : employees) {
			if (i.getEmail().equals(employee.getEmail())) {
				throw new IncorrectEmailException("This email already exists");
			}
		}
		employees.add(employee);
		id++;
	}

	public List<Employee> getAllEmployees() {
		return employees;
	}
}
