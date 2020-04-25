package dev.patel.services;

import java.util.List;

import dev.patel.daos.ReimbursementDAO;
import dev.patel.daos.ReimbursementDAOImpl;
import dev.patel.entities.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{

	
	private ReimbursementDAO reimburseDAO = new ReimbursementDAOImpl();
	@Override
	public Reimbursement submitReimbursement(Reimbursement reimbursement) {
		return reimburseDAO.createReimbursement(reimbursement);
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		return reimburseDAO.getAllReimbursements();
	}

	@Override
	public List<Reimbursement> getApprovedReimbursements() {
		return reimburseDAO.getApprovedReimbursements();
	}

	@Override
	public List<Reimbursement> getDeniedReimbursements() {
		return reimburseDAO.getDeniedReimbursements();
	}

	@Override
	public List<Reimbursement> getPendingReimbursements() {
		return reimburseDAO.getPendingReimbursements();
	}

	@Override
	public List<Reimbursement> getReimbursementByEmployeeId(int employeeId) {
		return reimburseDAO.getReimbursementByEmployeeId(employeeId);
	}

	@Override
	public List<Reimbursement> getPendingRequestsByEmployeeId(int employeeId) {
		return reimburseDAO.getPendingRequestsByEmployeeId(employeeId);
	}

	@Override
	public List<Reimbursement> getApprovedRequestsByEmployeeId(int employeeId) {
		return reimburseDAO.getApprovedRequestsByEmployeeId(employeeId);
	}

	@Override
	public List<Reimbursement> getReimbursementsByManagerId(int managerId) {
		return reimburseDAO.getReimbursementsByManagerId(managerId);
	}

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimbursement) {
		reimbursement.setStatus("Approved");
		return reimburseDAO.updateReimbursement(reimbursement);
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimbursement) {
		reimbursement.setStatus("Denied");
		return reimburseDAO.updateReimbursement(reimbursement);
	}

}
