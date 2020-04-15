package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Reimbursement;

public interface ReimbursementDAO {
	
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursementsById(int id);
	List<Reimbursement> getPendingReimbursements();
	List<Reimbursement> getApprovedReimbursements();
	List<Reimbursement> getReimbursementByEmployeeId(int employeeId);
	List<Reimbursement> getPendingRequestsByEmployeeId(int employeeId);
	List<Reimbursement> getApprovedRequestsByEmployeeId(int employeeId);
	
	List<Reimbursement> getReimbursementByManagerId(int mId);
	
	Reimbursement updateReimbursement(Reimbursement reimbursement);

}
