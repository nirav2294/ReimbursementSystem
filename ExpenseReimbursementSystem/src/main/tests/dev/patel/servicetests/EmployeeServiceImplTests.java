package dev.patel.servicetests;

import java.util.List;

import org.junit.Test;

import dev.patel.entities.Employee;
import dev.patel.exceptions.UserNotFoundException;
import dev.patel.services.EmployeeService;
import dev.patel.services.EmployeeServiceImpl;

public class EmployeeServiceImplTests {
	
	private EmployeeService empl = new EmployeeServiceImpl();
	@Test
	public void getAllEmployees(){
		System.out.println(empl.getAllEmployees());
	}
	
	@Test
	public void getEmployeeById() {
		System.out.println(empl.getEmployeeById(2));
	}
	
	@Test
	public void getEmployeeByUsername() {
		System.out.println(empl.getEmployeeByUsername("nirav2294"));
	}
	
	@Test
	public void validateEmployee() throws UserNotFoundException{
		System.out.println(empl.validateEmployee("nirav2294", "password"));
	}
	
	@Test
	public void updateEmployee() {
		
		Employee e = empl.getEmployeeById(5);
		e.setPassword("password");
		System.out.println(empl.updateEmployee(e));
	}

}
