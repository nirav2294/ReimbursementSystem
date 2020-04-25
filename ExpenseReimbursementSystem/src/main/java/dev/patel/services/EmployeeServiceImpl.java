package dev.patel.services;

import java.util.List;

import dev.patel.daos.EmployeeDAO;
import dev.patel.daos.EmployeeDAOImpl;
import dev.patel.entities.Employee;
import dev.patel.exceptions.UserNotFoundException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO emplDAO = new EmployeeDAOImpl();
	@Override
	public List<Employee> getAllEmployees() {
		
		return emplDAO.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return emplDAO.getEmployeeById(id);
	}

	@Override
	public Employee validateEmployee(String username, String password) throws UserNotFoundException {
		return emplDAO.validateEmployee(username, password);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return emplDAO.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		return emplDAO.deleteEmployee(employee);
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return emplDAO.getEmployeeByUsername(username);
	}

}
