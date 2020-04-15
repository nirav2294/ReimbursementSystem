package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Manager;

public interface ManagerDAO {
	
	
	List<Manager> getManagers();
	Manager getManagerByID(int id);
	Manager UpdateManager(Manager manager);
	boolean DeleteManager(Manager manager);

}
