package dev.patel.daotests;



import org.junit.Test;

import dev.patel.daos.ManagerDAO;
import dev.patel.daos.ManagerDAOImpl;
import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;

public class ManagerDAOImplTests {

	private ManagerDAO managerdao = new ManagerDAOImpl();
	
	@Test
	public void getAllManager() {
		System.out.println(managerdao.getAllManagers());
	}
	
	@Test
	public void getManagerById() {
		try {
			System.out.println(managerdao.getManagerById(5));
		} catch (Exception e) {
			
			System.out.println(e);
		}
	}
	
	@Test
	public void getManagerByUserName() {
		System.out.println(managerdao.getManagerByUsername("niravpatel2294"));
	}
	
	@Test
	public void validateManager() throws ManagerNotFoundException {
		System.out.println(managerdao.validateManager("abhi23", "23abhi"));
	}
	
	@Test
	public void updateManager() {
		Manager manager;
		try {
			manager = managerdao.getManagerById(3);
			manager.setPassword("meet0488");
			System.out.println(managerdao.updateManager(manager));
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@Test
	public void deleteManager() {
		Manager manager;
		try {
			manager = managerdao.getManagerById(3);
			System.out.println(managerdao.deleteManager(manager));
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}


}
