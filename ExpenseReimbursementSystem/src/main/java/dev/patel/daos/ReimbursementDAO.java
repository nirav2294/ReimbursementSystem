package dev.patel.daos;

import java.util.List;

import dev.patel.entities.Reimbursement;

public interface ReimbursementDAO {
	
	Reimbursement createReimbursement(Reimbursement reimbursement);
	
	List<Reimbursement> getAllReimbursements();
	Reimbursement getReimbursementById(int id);
	List<Reimbursement> getPendingReimbursements();
	List<Reimbursement> getApprovedReimbursements();
	List<Reimbursement> getDeniedReimbursements();
	List<Reimbursement> getReimbursementByEmployeeId(int employeeId);
	List<Reimbursement> getPendingRequestsByEmployeeId(int employeeId);
	List<Reimbursement> getApprovedRequestsByEmployeeId(int employeeId);
	
	List<Reimbursement> getReimbursementsByManagerId(int managerId);
	
	Reimbursement updateReimbursement(Reimbursement reimbursement);

}
