package dev.patel.entities;

import java.sql.Date;

public class Reimbursement {
	
	private int reimbursementId;
	private int employeeId;
	private int managerId;
	private String status;
	private double amount;
	private Date requestedDate;
	private String description;
	private Date expenseDate;
	private String type;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, int employeeId, int managerId, String status, double amount,
			Date requestedDate, String description, Date expenseDate, String type) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.status = status;
		this.amount = amount;
		this.requestedDate = requestedDate;
		this.description = description;
		this.expenseDate = expenseDate;
		this.type = type;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", managerId="
				+ managerId + ", status=" + status + ", amount=" + amount + ", requestedDate=" + requestedDate
				+ ", description=" + description + ", expenseDate=" + expenseDate + ", type=" + type + "]";
	}
	
	
	
	
	

}
