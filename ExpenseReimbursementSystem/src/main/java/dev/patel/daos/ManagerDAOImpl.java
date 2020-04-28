package dev.patel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;
import dev.patel.utils.ConnectionUtil;

public class ManagerDAOImpl implements ManagerDAO {

	@Override
	public List<Manager> getAllManagers() {
		String sql = "SELECT * FROM Project1.MANAGER";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Manager> managers = new ArrayList<Manager>();
			while (rs.next()) {
				Manager manager = new Manager(rs.getInt("MANAGER_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				managers.add(manager);
			}
			return managers;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Manager getManagerById(int id) throws ManagerNotFoundException {
		String sql = "SELECT * FROM Project1.MANAGER WHERE MANAGER_ID= ?";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Manager manager = new Manager(rs.getInt("MANAGER_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				return manager;
			} else {
				System.out.println("Manager Not Found");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		throw new ManagerNotFoundException("No manager found with that Id");
	}

	@Override
	public Manager getManagerByUsername(String username) {
		String sql = "SELECT * FROM Project1.MANAGER WHERE USERNAME = ?";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Manager manager = new Manager(rs.getInt("MANAGER_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				return manager;
			} else {
				System.out.println("Manager Not Found");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Manager validateManager(String username, String password) throws ManagerNotFoundException {
		String sql = "SELECT * FROM Project1.MANAGER ";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString("USERNAME").equals(username) && rs.getString("PASSWORD").equals(password)) {
					Manager manager = new Manager(rs.getInt("MANAGER_ID"), rs.getString("FIRST_NAME"),
							rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
							rs.getString("PASSWORD"));
					return manager;
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Wrong username or password!!");
			return null;
		}
		throw new ManagerNotFoundException("Incorrect username or password");
	}

	@Override
	public Manager updateManager(Manager manager) {

		String sql = "UPDATE Project1.MANAGER SET FIRST_NAME = ?, LAST_NAME=?,USERNAME = ?, EMAIL=?,PASSWORD = ? WHERE MANAGER_ID =?";
		try (Connection conn = ConnectionUtil.createConnection()) {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getFirstName());
			ps.setString(2, manager.getLastName());
			ps.setString(3, manager.getUserName());
			ps.setString(4, manager.getEmail());
			ps.setString(5, manager.getPassword());
			ps.setInt(6, manager.getManagerId());
			ps.executeUpdate();
			return manager;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return manager;
	}

	@Override
	public boolean deleteManager(Manager manager) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String Query = "DELETE FROM Project1.MANAGER WHERE MANAGER_ID = ?";
			PreparedStatement post = conn.prepareStatement(Query);
			post.setInt(1, manager.getManagerId());
			boolean success = post.execute();
			return success;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return true;
	}

}
