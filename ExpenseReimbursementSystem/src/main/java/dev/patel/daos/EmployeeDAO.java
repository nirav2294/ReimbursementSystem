package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Employee;

public interface EmployeeDAO {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	Employee getEmployeeByUsername(String username);
	Employee validateEmployee(String username, String password);

	Employee updateEmployee(Employee employee);
	boolean deleteEmployee(Employee employee);


}
