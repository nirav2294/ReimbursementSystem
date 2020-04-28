package dev.patel.services;

import java.util.List;

import dev.patel.daos.ReimbursementTypeDAO;
import dev.patel.daos.ReimbursementTypeDAOImpl;
import dev.patel.entities.ReimbursementType;

public class ReimbursementTypeServiceImpl implements ReimbursementTypeService{

	private ReimbursementTypeDAO typeDAO = new ReimbursementTypeDAOImpl() ;
		
		@Override
		public List<ReimbursementType> getTypeByEmployeeId(int id) {
			return typeDAO.getTypeByEmployeeId(id);
		}
		
		@Override
		public ReimbursementType createType(ReimbursementType r) {
			return typeDAO.createType(r);
		}
	
	
}
