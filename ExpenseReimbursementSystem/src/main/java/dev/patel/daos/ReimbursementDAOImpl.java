package dev.patel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dev.patel.entities.Manager;
import dev.patel.entities.Reimbursement;
import dev.patel.utils.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{

	static java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		String sql = "INSERT INTO Project1.REIMBURSEMENT VALUES(?,?,?,?,?,?,?,?,?)";
		try(Connection conn = ConnectionUtil.createConnection()){
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 0);
			ps.setInt(2, reimbursement.getEmployeeId());
			ps.setInt(3, reimbursement.getManagerId());
			ps.setString(4, reimbursement.getStatus());
			ps.setDouble(5, reimbursement.getAmount());
			ps.setDate(6, date);
			ps.setString(7, reimbursement.getDescription());
			ps.setDate(8, reimbursement.getExpenseDate());
			ps.setString(9, reimbursement.getType());
			
			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("REIMBURSEMENT_ID");
			reimbursement.setReimbursementId(key);
			
			return reimbursement;
		} catch (SQLException e) {
			System.out.println("Error creating Reimbursement");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {

		String sql = "SELECT * FROM Project1.REIMBURSEMENT";
		try (Connection conn = ConnectionUtil.createConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"),
						rs.getInt("MANAGER_ID"), rs.getString("STATUS"), rs.getDouble("AMOUNT"),
						rs.getDate("REQUESTED_DATE"), rs.getString("DESCRIPTION"), rs.getDate("EXPENSE_DATE"),
						rs.getString("REIMBURSEMENT_TYPE"));
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return null;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		for(int i=0; i<reimbursements.size();i++) {
			if(reimbursements.get(i).getReimbursementId() == id) {
				return reimbursements.get(i);
			}
			
		}
		return null;
	}

	@Override
	public List<Reimbursement> getPendingReimbursements() {

		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getStatus().equals("Pending")) {
				pendingReimbursements.add(reimbursements.get(i));
			}
		}
		return pendingReimbursements;
	}

	@Override
	public List<Reimbursement> getApprovedReimbursements() {
		
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> approvedReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getStatus().equals("Approved")) {
				approvedReimbursements.add(reimbursements.get(i));
			}
		}
		return approvedReimbursements;
	}
	
	@Override
	public List<Reimbursement> getDeniedReimbursements() {
		
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> deniedReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getStatus().equals("Denied")) {
				deniedReimbursements.add(reimbursements.get(i));
			}
		}
		return deniedReimbursements;

	}

	@Override
	public List<Reimbursement> getReimbursementByEmployeeId(int employeeId) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> employeeReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getEmployeeId() == employeeId) {
				employeeReimbursements.add(reimbursements.get(i));
			}
		}
		return employeeReimbursements;

	}

	@Override
	public List<Reimbursement> getPendingRequestsByEmployeeId(int employeeId) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> pendingReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getStatus().equals("Pending") && reimbursements.get(i).getEmployeeId() == employeeId) {
				pendingReimbursements.add(reimbursements.get(i));
			}
		}
		return pendingReimbursements;
	}

	@Override
	public List<Reimbursement> getApprovedRequestsByEmployeeId(int employeeId) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> approvedReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getStatus().equals("Approved") && reimbursements.get(i).getEmployeeId() == employeeId) {
				approvedReimbursements.add(reimbursements.get(i));
			}
		}
		return approvedReimbursements;
	}

	@Override
	public List<Reimbursement> getReimbursementsByManagerId(int managerId) {
		List<Reimbursement> reimbursements = this.getAllReimbursements();
		List<Reimbursement> managerReimbursements = new ArrayList<Reimbursement>();
		for(int i=0; i<reimbursements.size(); i++) {
			if(reimbursements.get(i).getManagerId() == managerId) {
				managerReimbursements.add(reimbursements.get(i));
			}
		}
		return managerReimbursements;
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		String sql = "UPDATE Project1.Reimbursement SET MANAGER_ID=?, STATUS=?, AMOUNT=?, DESCRIPTION=?, EXPENSE_DATE=? , REIMBURSEMENT_TYPE=? WHERE EMPLOYEE_ID=?";
		try(Connection conn = ConnectionUtil.createConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimbursement.getManagerId());
			ps.setString(2, reimbursement.getStatus());
			ps.setDouble(3, reimbursement.getAmount());
			ps.setString(4, reimbursement.getDescription());
			ps.setDate(5, reimbursement.getExpenseDate());
			ps.setString(6, reimbursement.getType());
			ps.setInt(7, reimbursement.getEmployeeId());
			
		} catch (SQLException e) {
			System.out.println("Update reimbursement successful");
			e.printStackTrace();
		}
		return null;
	}

}
