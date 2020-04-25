package dev.patel.services;

import java.util.List;

import dev.patel.daos.ManagerDAO;
import dev.patel.daos.ManagerDAOImpl;
import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;

public class ManagerServiceImpl implements ManagerService{

	private ManagerDAO managerDAO = new ManagerDAOImpl();
	@Override
	public List<Manager> getAllManagers() {
		return managerDAO.getAllManagers();
	}

	@Override
	public Manager validateManager(String username, String password) throws ManagerNotFoundException {
		return managerDAO.validateManager(username, password);
	}

	@Override
	public Manager updateManager(Manager manager) {
		return managerDAO.updateManager(manager);
	}

	@Override
	public boolean deleteManager(Manager manager) {
		return managerDAO.deleteManager(manager);
	}

	@Override
	public Manager getManagerById(int id) throws Exception {
		return managerDAO.getManagerById(id);
	}

}
