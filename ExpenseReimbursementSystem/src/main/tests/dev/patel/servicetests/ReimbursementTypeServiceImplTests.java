package dev.patel.servicetests;


import org.junit.Test;

import dev.patel.entities.ReimbursementType;
import dev.patel.services.ReimbursementTypeService;
import dev.patel.services.ReimbursementTypeServiceImpl;

public class ReimbursementTypeServiceImplTests {
	
	private ReimbursementTypeService rtype = new ReimbursementTypeServiceImpl();
	
	@Test
	public void createType() {
		
		ReimbursementType r = new ReimbursementType(5 , "EMP");
		System.out.println(rtype.createType(r));
	}
	@Test
	public void getTypeByEmployeeId() {
		System.out.println(rtype.getTypeByEmployeeId(5));
	}

}
