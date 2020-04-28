package dev.patel.services;

import java.util.List;

import dev.patel.entities.ReimbursementType;

public interface ReimbursementTypeService {

	ReimbursementType createType(ReimbursementType r);
	List<ReimbursementType> getTypeByEmployeeId(int id);
}
