package dev.patel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.patel.entities.Employee;
import dev.patel.exceptions.UserNotFoundException;
import dev.patel.utils.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<Employee> getAllEmployees() {

		String sql = "SELECT * FROM Project1.EMPLOYEE";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "SELECT * FROM Project1.EMPLOYEE WHERE EMPLOYEE_ID = ?";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				return employee;
			} else {
				System.out.println("Employee Not Found");
			}

		} catch (SQLException e) {
			System.out.println(e);
		} 
		return null;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		String sql = "SELECT * FROM Project1.EMPLOYEE WHERE USERNAME = ?";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
				return employee;
			} else {
				System.out.println("Employee Not Found");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Employee validateEmployee(String username, String password) throws UserNotFoundException {
		String sql = "SELECT * FROM Project1.EMPLOYEE ";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString("USERNAME").equals(username) && rs.getString("PASSWORD").equals(password)) {
					Employee employee = new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("FIRST_NAME"),
							rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("EMAIL"),
							rs.getString("PASSWORD"));
					return employee;
				} 

			}
			
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Wrong username or password!!");
			return null;
		} 
		throw new UserNotFoundException("Invalid Username or Password!!");
	}

	public Employee updateEmployee(Employee employee) {
		try (Connection con = ConnectionUtil.createConnection()){
			String sql = "UPDATE Project1.EMPLOYEE SET FIRST_NAME = ?, LAST_NAME=?,USERNAME = ?, EMAIL=?,PASSWORD = ? WHERE EMPLOYEE_ID =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getUserName());
			ps.setString(4, employee.getEmail());
			ps.setString(5, employee.getPassword());
			ps.setInt(6, employee.getEmployeeId());
			ps.executeUpdate();
			return employee;
		}catch (SQLException e) {
			System.out.println(e);
		}
		return employee;
	}

	public boolean deleteEmployee(Employee employee) {
		try (Connection con = ConnectionUtil.createConnection()){
			String Query = "DELETE FROM Project1.EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement post = con.prepareStatement(Query);
			post.setInt(1,employee.getEmployeeId());
			boolean success = post.execute();
			return success;
		}catch (SQLException e) {
			System.out.println(e);
		}
		return true;
	}



}
