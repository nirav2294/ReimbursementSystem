package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;

public interface ManagerDAO {
	
	
	List<Manager> getAllManagers();
	Manager getManagerById(int id) throws Exception;
	Manager getManagerByUsername(String username);
	Manager validateManager(String username, String password) throws ManagerNotFoundException;
	Manager updateManager(Manager manager);
	boolean deleteManager(Manager manager);

}
