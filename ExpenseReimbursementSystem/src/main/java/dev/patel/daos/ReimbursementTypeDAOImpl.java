package dev.patel.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.patel.entities.ReimbursementType;
import dev.patel.utils.ConnectionUtil;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO{

	@Override
	public ReimbursementType createType(ReimbursementType r) {
		
		String sql = "INSERT INTO Project1.R_TYPE VALUES (?,?)";
		try(Connection conn = ConnectionUtil.createConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, r.getEmployeeId());
			ps.setString(2, r.getReimbursementType());
			ps.execute();
			return r;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return r;
	}

	@Override
	public List<ReimbursementType> getTypeByEmployeeId(int id) {
		String sql = "SELECT * FROM Project1.R_TYPE WHERE EMPLOYEE_ID =?";
		try(Connection conn = ConnectionUtil.createConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<ReimbursementType> types = new ArrayList<ReimbursementType>();
			while(rs.next()) {
				ReimbursementType r = new ReimbursementType(rs.getInt("EMPLOYEE_ID"), rs.getString("TYPE"));
				types.add(r);
			}
			return types;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
