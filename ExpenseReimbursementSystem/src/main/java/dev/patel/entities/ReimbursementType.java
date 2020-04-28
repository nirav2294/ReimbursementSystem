package dev.patel.entities;

public class ReimbursementType {
	
	
	private int employeeId;
	private String reimbursementType;
	public String getReimbursementType() {
		return reimbursementType;
	}
	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public ReimbursementType( int employeeId,String reimbursementType) {
		super();
		this.reimbursementType = reimbursementType;
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "ReimbursementType [employeeId=" + employeeId + "reimbursementType=" + reimbursementType + "]";
	}
	
	

}
