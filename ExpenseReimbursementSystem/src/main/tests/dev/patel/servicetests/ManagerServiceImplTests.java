package dev.patel.servicetests;

import java.util.List;

import org.junit.Test;

import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;
import dev.patel.services.ManagerService;
import dev.patel.services.ManagerServiceImpl;

public class ManagerServiceImplTests {
	
	private ManagerService mserv = new ManagerServiceImpl();
	
	@Test
	public void getAllManagers() {
		System.out.println(mserv.getAllManagers());
	}
	
	@Test
	public void getManagerById() throws Exception{
		System.out.println(mserv.getManagerById(2));
	}
	
	@Test
	public void validateManager() throws ManagerNotFoundException{
		System.out.println(mserv.validateManager("niravpatel2294", "password"));
	}
	@Test
	public void updateManager() throws Exception {
		Manager manager = mserv.getManagerById(2);
		manager.setFirstName("Abhishek");
		System.out.println(mserv.updateManager(manager));
	}

}
