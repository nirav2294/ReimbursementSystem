package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Employee;
import dev.patel.exceptions.UserNotFoundException;

public interface EmployeeDAO {
	
	List<Employee> getAllEmployees();
	Employee getEmployeeById(int id);
	Employee getEmployeeByUsername(String username);
	Employee validateEmployee(String username, String password) throws UserNotFoundException;
	Employee updateEmployee(Employee employee);
	boolean deleteEmployee(Employee employee);

}
