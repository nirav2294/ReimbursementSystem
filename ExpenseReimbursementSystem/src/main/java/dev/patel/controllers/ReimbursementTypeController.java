package dev.patel.controllers;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dev.patel.entities.Employee;
import dev.patel.entities.Reimbursement;
import dev.patel.entities.ReimbursementType;
import dev.patel.services.EmployeeService;
import dev.patel.services.EmployeeServiceImpl;
import dev.patel.services.ReimbursementService;
import dev.patel.services.ReimbursementServiceImpl;
import dev.patel.services.ReimbursementTypeService;
import dev.patel.services.ReimbursementTypeServiceImpl;

public class ReimbursementTypeController {
	static java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

	private ReimbursementTypeService rType = new ReimbursementTypeServiceImpl();
	private ReimbursementService rserv = new ReimbursementServiceImpl();
	private EmployeeService emplService = new EmployeeServiceImpl();
	Gson gson = new Gson();

	public void getAllTypes(HttpServletRequest request, HttpServletResponse response) {

		try {
			String username = (String) request.getSession().getAttribute("username");
			Employee employee = emplService.getEmployeeByUsername(username);
			List<ReimbursementType> rtypes = rType.getTypeByEmployeeId(employee.getEmployeeId());
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(rtypes);
			System.out.println(json);
			pw.append(json);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public void submitReimbursement(HttpServletRequest request, HttpServletResponse response) {

		try {

			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			PrintWriter pw = response.getWriter();
			String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
			//System.out.println(body);
			Reimbursement r = gson.fromJson(body, Reimbursement.class);
			r.setRequestedDate(date);
			r.setStatus("Pending");
			System.out.println(r);
			r = rserv.submitReimbursement(r);
			String json = gson.toJson(r);
			pw.append(json);


		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
