package dev.patel.daotests;

import org.junit.Test;

import dev.patel.daos.ReimbursementTypeDAO;
import dev.patel.daos.ReimbursementTypeDAOImpl;
import dev.patel.entities.ReimbursementType;

public class ReimbursementTypeDAOImplTests {
	
	private ReimbursementTypeDAO type = new ReimbursementTypeDAOImpl();
	
	@Test
	public void createReimbursementType() {
		ReimbursementType r = new ReimbursementType(5, "Explosives");
		type.createType(r);
	}
	
	@Test
	public void getTypeByEmployeeID() {
		System.out.println(type.getTypeByEmployeeId(5));
	}

}
