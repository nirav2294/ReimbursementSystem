package dev.patel.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.patel.entities.Employee;
import dev.patel.entities.Manager;
import dev.patel.entities.Reimbursement;
import dev.patel.services.EmployeeService;
import dev.patel.services.EmployeeServiceImpl;
import dev.patel.services.ManagerService;
import dev.patel.services.ManagerServiceImpl;
import dev.patel.services.ReimbursementService;
import dev.patel.services.ReimbursementServiceImpl;

public class StatsController {

	private ReimbursementService rserv = new ReimbursementServiceImpl();
	private EmployeeService emplService = new EmployeeServiceImpl();
	private ManagerService mserv = new ManagerServiceImpl();
	Gson gson = new Gson();

	public void getManagerApproveStats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ((String) request.getSession().getAttribute("username") == null) {
			response.sendError(403);
		} else {
			PrintWriter pw = response.getWriter();
			List<Manager> managers = mserv.getAllManagers();
			List<Double> approved = new ArrayList<Double>();

			double totalApproved = 0;
			for (int i = 0; i < managers.size(); i++) {
				List<Reimbursement> reimbursements = rserv
						.getApprovedRequestsByManagerId(managers.get(i).getManagerId());
				for (int k = 0; k < reimbursements.size(); k++) {
					if (reimbursements.get(k).getStatus().equals("Approved")) {
						totalApproved += reimbursements.get(k).getAmount();
					}
				}

				approved.add(totalApproved);
				totalApproved = 0;
			}
			String json = gson.toJson(approved);
			pw.append(json);
		}

	}

	public void getEmployeeRequestStats(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if ((String) request.getSession().getAttribute("username") == null) {
			response.sendError(403);
		} else {
			PrintWriter pw = response.getWriter();
			List<Employee> employees = emplService.getAllEmployees();
			List<Double> approved = new ArrayList<Double>();
			HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
			int totalRequests = 0;
			for (int i = 0; i < employees.size(); i++) {
				List<Reimbursement> reimbursements = rserv
						.getReimbursementByEmployeeId(employees.get(i).getEmployeeId());
				for (int k = 0; k < reimbursements.size(); k++) {
					if (reimbursements.get(k).getEmployeeId()== employees.get(i).getEmployeeId()) {
						totalRequests += 1;
					}
				}

				hmap.put(employees.get(i).getEmployeeId(), totalRequests);
				totalRequests = 0;
			}
			String json = gson.toJson(hmap);
			pw.append(json);
		}

	}

	public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<Employee> employees = emplService.getAllEmployees();
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(employees);
			System.out.println(json);
			pw.append(json);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void approveDeny(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter pw = response.getWriter();
		List<Reimbursement> approved = rserv.getApprovedReimbursements();
		List<Reimbursement> denied = rserv.getDeniedReimbursements();
		HashMap<String, Double> hmap = new HashMap<String, Double>();
		double total =0;
		for(int i=0; i<approved.size(); i++) {
			total+= approved.get(i).getAmount();
		}
		hmap.put("Approved", total);
		total=0;
		for(int i=0; i<denied.size(); i++) {
			total+= denied.get(i).getAmount();
		}
		hmap.put("Denied", total);
		hmap.put("ApprovedRequests", (double) approved.size());
		hmap.put("DeniedRequests", (double) denied.size());
		String json = gson.toJson(hmap);
		pw.append(json);
	}

}
