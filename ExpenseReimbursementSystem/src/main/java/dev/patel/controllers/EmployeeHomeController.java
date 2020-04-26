package dev.patel.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.patel.entities.Employee;
import dev.patel.entities.Manager;
import dev.patel.entities.Reimbursement;
import dev.patel.services.EmployeeService;
import dev.patel.services.EmployeeServiceImpl;
import dev.patel.services.ManagerService;
import dev.patel.services.ManagerServiceImpl;
import dev.patel.services.ReimbursementService;
import dev.patel.services.ReimbursementServiceImpl;

public class EmployeeHomeController {

	private ReimbursementService reimburseService = new ReimbursementServiceImpl();
	private EmployeeService emplService = new EmployeeServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();
	Gson gson = new Gson();

	public void displayReimbursement(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter pw;
		try {

			pw = response.getWriter();
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				String username = (String) request.getSession().getAttribute("username");
				Employee user = emplService.getEmployeeByUsername(username);
				if (user != null) {
					List<Reimbursement> reimbursements = reimburseService
							.getReimbursementByEmployeeId(user.getEmployeeId());
					String json = gson.toJson(reimbursements);
					pw.append(json);

				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void getPendingRequests(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter pw;
		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				pw = response.getWriter();
				String username = (String) request.getSession().getAttribute("username");
				Employee user = emplService.getEmployeeByUsername(username);
				if (user != null) {
					List<Reimbursement> reimbursements = reimburseService
							.getPendingRequestsByEmployeeId(user.getEmployeeId());
					String json = gson.toJson(reimbursements);
					pw.append(json);

				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void getApprovedRequests(HttpServletRequest request, HttpServletResponse response) {

		PrintWriter pw;
		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				pw = response.getWriter();
				String username = (String) request.getSession().getAttribute("username");
				Employee user = emplService.getEmployeeByUsername(username);
				if (user != null) {
					List<Reimbursement> reimbursements = reimburseService
							.getApprovedRequestsByEmployeeId(user.getEmployeeId());
					String json = gson.toJson(reimbursements);
					pw.append(json);

				} else {
					System.out.println("user is null at getApprovedRequests");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void getCurrentUser(HttpServletRequest request, HttpServletResponse response) {

		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				PrintWriter pw = response.getWriter();
				String username = (String) request.getSession().getAttribute("username");
				Employee user = emplService.getEmployeeByUsername(username);
				if (user != null) {
					String json = gson.toJson(user);
					pw.append(json);
				} else {
					System.out.println("user is null at getCurrentUser");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void submitReimbursement(HttpServletRequest request, HttpServletResponse response) {

	}

	public void updateEmployeeProfile(HttpServletRequest request, HttpServletResponse response) {
		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} 
			else {
				ObjectMapper mapper = new ObjectMapper();
				String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
				Employee employee = gson.fromJson(body, Employee.class);
				Employee currentDetails = emplService.getEmployeeById(employee.getEmployeeId());

				List<Employee> employees = emplService.getAllEmployees();
				for(int i=0; i<employees.size(); i++) {
					if(employees.get(i).getEmployeeId() == employee.getEmployeeId()) {
						employees.remove(i);
					}
				}
				boolean exists = false;
				for(int i=0; i<employees.size(); i++) {
					if(employees.get(i).getEmail().equals(employee.getEmail()) ) {
						response.setHeader("Content-Type", "application/json");
						mapper.writeValue(response.getOutputStream(), true);
						exists = true;
					}
					else if(employees.get(i).getUserName().equals(employee.getUserName())) {
						response.setHeader("Content-Type", "application/json");
						mapper.writeValue(response.getOutputStream(), false);
						exists = true;
					}
				}
				if(exists == false) {
					Employee updateEmployee = new Employee(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), 
							employee.getUserName(), employee.getEmail(), currentDetails.getPassword());
					emplService.updateEmployee(updateEmployee);
					HttpSession sess = request.getSession();
					sess.setAttribute("username", updateEmployee.getUserName());
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				ObjectMapper mapper = new ObjectMapper();
				String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
				Employee employee = gson.fromJson(body, Employee.class);
				Employee currentDetails = emplService.getEmployeeById(employee.getEmployeeId());

				if(employee.getFirstName().equals(currentDetails.getPassword())) { // had to user FirstName to store old password coming from user
					Employee updateEmployee = new Employee(currentDetails.getEmployeeId(), currentDetails.getFirstName(), currentDetails.getLastName(), 
							currentDetails.getUserName(), currentDetails.getEmail(), employee.getPassword());
					emplService.updateEmployee(updateEmployee);
					response.setHeader("Content-Type", "application/json");
					mapper.writeValue(response.getOutputStream(), true);
				}
				else {
					response.setHeader("Content-Type", "application/json");
					mapper.writeValue(response.getOutputStream(), false);
				}

			}
		}catch (IOException e) {
			System.out.println(e);
		}
	}

	public void getEmployeeName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Employee> employees = new ArrayList<Employee>();
			PrintWriter pw = response.getWriter();
			String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
			for(int i=2; i<body.length()-2; i+=2) {
				char temp = body.charAt(i);
				int id = Character.getNumericValue(temp);

				employees.add(emplService.getEmployeeById(id));

			} 
			String json = gson.toJson(employees);
			System.out.println(json);
			pw.append(json);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void approveReimbursement(HttpServletRequest request, HttpServletResponse response) {

		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				String managerUsername = (String) request.getSession().getAttribute("username");
				Manager manager = managerService.getManagerByUsername(managerUsername);
				PrintWriter pw = response.getWriter();
				String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
				Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
				reimbursement = reimburseService.getReimbursementById(reimbursement.getReimbursementId());
				reimbursement.setStatus("Approved");
				reimbursement.setManagerId(manager.getManagerId());
				reimburseService.approveReimbursement(reimbursement);
			}
		} catch(Exception e) {
			System.out.println(e);
		}

	}

	public void denyReimbursement(HttpServletRequest request, HttpServletResponse response) {

		try {
			if ((String) request.getSession().getAttribute("username") == null) {
				response.sendError(403);
			} else {
				String managerUsername = (String) request.getSession().getAttribute("username");
				Manager manager = managerService.getManagerByUsername(managerUsername);
				PrintWriter pw = response.getWriter();
				String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
				Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
				reimbursement = reimburseService.getReimbursementById(reimbursement.getReimbursementId());
				reimbursement.setStatus("Denied");
				reimbursement.setManagerId(manager.getManagerId());
				reimburseService.denyReimbursement(reimbursement);
			}
		} catch(Exception e) {
			System.out.println(e);
		}

	}

}
