package dev.patel.daotests;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dev.patel.daos.ReimbursementDAO;
import dev.patel.daos.ReimbursementDAOImpl;
import dev.patel.entities.Reimbursement;

public class ReimbursementDAOImplTests {

	private ReimbursementDAO reimbursedao = new ReimbursementDAOImpl();
	@Test
	public void createReimbursement() throws ParseException {
		java.sql.Date sDate = new java.sql.Date(
                ((java.util.Date) new SimpleDateFormat("ddMMyyyy").parse("20112019")).getTime());
		Reimbursement r = new Reimbursement(0, 2, 1, "Pending", 143, null, "Charger for work laptop", sDate, "work expense");
		System.out.println(reimbursedao.createReimbursement(r));
	}
	
	@Test
	public void getAllReimbursements() {
		System.out.println(reimbursedao.getAllReimbursements());
	}
	
	@Test
	public void getReimbursementById() {
		System.out.println(reimbursedao.getReimbursementById(4));
	}
	
	@Test
	public void getPendingReimbursements() {
		System.out.println(reimbursedao.getPendingReimbursements());
	}
	
	@Test
	public void getApprovedReimbursements() {
		System.out.println(reimbursedao.getApprovedReimbursements());
	}
	
	@Test
	public void getDeniedReimbursements() {
		System.out.println(reimbursedao.getDeniedReimbursements());
	}
	
	@Test
	public void getReimbursementByEmployeeId() {
		System.out.println(reimbursedao.getReimbursementByEmployeeId(2));
	}
	
	@Test
	public void getApprovedRequestsByEmployeeId() {
		System.out.println(reimbursedao.getApprovedRequestsByEmployeeId(1));
	}
	
	@Test
	public void getReimbursementsByManagerId() {
		System.out.println(reimbursedao.getReimbursementsByManagerId(2));
	}
	
	
	
}

	
	



