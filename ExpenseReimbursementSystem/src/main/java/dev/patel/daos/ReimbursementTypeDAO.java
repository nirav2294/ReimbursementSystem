package dev.patel.daos;

import java.util.List;

import dev.patel.entities.ReimbursementType;

public interface ReimbursementTypeDAO {

	
	ReimbursementType createType(ReimbursementType r);
	List<ReimbursementType> getTypeByEmployeeId(int id);
	
}
