package dev.patel.servicetests;

import java.util.List;

import org.junit.Test;

import dev.patel.entities.Reimbursement;
import dev.patel.services.ReimbursementService;
import dev.patel.services.ReimbursementServiceImpl;

public class ReimbursementServiceImplTests {
	
	private ReimbursementService  rserv = new ReimbursementServiceImpl();
	
	@Test
	public void submitReimbursement() {
		
	}
	
	@Test
	public void getAllReimbursements() {
		System.out.println(rserv.getAllReimbursements());
	}
	
	@Test
	public void getApprovedReimbursements() {
		System.out.println(rserv.getApprovedReimbursements());
	}
	
	@Test
	public void getDeniedReimbursements() {
		System.out.println(rserv.getDeniedReimbursements());
	}
	
	@Test
	public void getPendingReimbursements() {
		System.out.println(rserv.getPendingReimbursements());
	}
	
	@Test
	public void getReimbursementByEmployeeId() {
		System.out.println(rserv.getReimbursementByEmployeeId(1));
	}
	
	@Test
	public void getPendingRequestsByEmployeeId() {
		System.out.println(rserv.getPendingRequestsByEmployeeId(2));
	}
	
	@Test
	public void getApprovedRequestsByEmployeeId() {
		System.out.println(rserv.getApprovedRequestsByEmployeeId(3));
	}
	
	@Test
	public void getReimbursementsByManagerId() {
		System.out.println(rserv.getReimbursementsByManagerId(1));
	}
	
	
	@Test
	public void approveReimbursement() {
		
		Reimbursement reimbursement = rserv.getReimbursementById(2);
		reimbursement.setStatus("Approved");
		rserv.approveReimbursement(reimbursement);
	}
	@Test
	public void denyRembursement() {
		
		
	}
	
	@Test
	public void getPendingRequestsByManagerId() {
		System.out.println(rserv.getPendingRequestsByManagerId(1));
	}
	@Test
	public void getApprovedRequestsByManagerId() {
		System.out.println(rserv.getApprovedRequestsByManagerId(2));
	}
	@Test
	public void getDeniedRequestsByManagerId() {
		System.out.println(rserv.getDeniedRequestsByManagerId(2));
	}
}
