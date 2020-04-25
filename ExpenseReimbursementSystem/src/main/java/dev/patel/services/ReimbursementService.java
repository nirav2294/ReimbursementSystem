package dev.patel.services;

import java.util.List;

import dev.patel.entities.Reimbursement;

public interface ReimbursementService {
	
	Reimbursement submitReimbursement(Reimbursement reimbursement);
	List<Reimbursement> getAllReimbursements();
	List<Reimbursement> getApprovedReimbursements();
	List<Reimbursement> getDeniedReimbursements();
	List<Reimbursement> getPendingReimbursements();
	List<Reimbursement> getReimbursementByEmployeeId(int employeeId);
	List<Reimbursement> getPendingRequestsByEmployeeId(int employeeId);
	List<Reimbursement> getApprovedRequestsByEmployeeId(int employeeId);
	List<Reimbursement> getReimbursementsByManagerId(int managerId);
	
	
	Reimbursement approveReimbursement(Reimbursement reimbursement);
	Reimbursement denyReimbursement(Reimbursement reimbursement);


}
