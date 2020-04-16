package dev.patel.daotests;


import org.junit.Test;

import dev.patel.daos.EmployeeDAO;
import dev.patel.daos.EmployeeDAOImpl;
import dev.patel.entities.Employee;

public class EmployeeDAOImplTests {

	private EmployeeDAO empldao = new EmployeeDAOImpl();
	@Test
	public void getAllEmployees() {
		System.out.println(empldao.getAllEmployees());
	}
	
	@Test
	public void getEmployeeById() {
		System.out.println(empldao.getEmployeeById(2));
	}

	@Test
	public void getEmployeeByUserName() {
		System.out.println(empldao.getEmployeeByUsername("nirav2294"));
	}
	
	@Test
	public void validateEmployee() {
		System.out.println(empldao.validateEmployee("kushal104", "104kushal"));
	}
	
	@Test
	public void updateEmployee() {
		Employee employee = empldao.getEmployeeById(3);
		employee.setPassword("meet0488");
		System.out.println(empldao.updateEmployee(employee));
	}
	
	@Test
	public void deleteEmployee() {
		Employee employee = empldao.getEmployeeById(3);
		System.out.println(empldao.deleteEmployee(employee));
	}
	
	
}
