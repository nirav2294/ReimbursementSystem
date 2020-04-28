package dev.patel.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.patel.controllers.EmployeeHomeController;
import dev.patel.controllers.LoginController;
import dev.patel.controllers.ManagerController;
import dev.patel.controllers.ReimbursementTypeController;
import dev.patel.controllers.StatsController;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();
	}

	LoginController loginController = new LoginController();
	EmployeeHomeController employeeController = new EmployeeHomeController();
	ManagerController managerController = new ManagerController();
	ReimbursementTypeController rTypeController = new ReimbursementTypeController();
	StatsController statsController = new StatsController();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		System.out.println(uri);
		switch (uri) {


		case "/ExpenseReimbursementSystem/api/LoginPage":
			loginController.loginEmployee(request, response);
			break;

		case "/ExpenseReimbursementSystem/api/EmployeeHome":
			employeeController.displayReimbursement(request, response);
			break;
			
		case "/ExpenseReimbursementSystem/api/ManagerDetails":
			managerController.getManagerName(request, response);
			break;
			
		case "/ExpenseReimbursementSystem/api/PendingRequests":
			employeeController.getPendingRequests(request, response);
			break;

		case "/ExpenseReimbursementSystem/api/ApprovedRequests":
			employeeController.getApprovedRequests(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getAllManagers":
			managerController.getAllManagers(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getCurrentUser":
			employeeController.getCurrentUser(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/UpdateProfile":
			employeeController.updateEmployeeProfile(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/UpdatePassword":
			employeeController.updatePassword(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/Logout":
			loginController.logout(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/ManagerLoginPage":
			loginController.managerLogin(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/ManagerHome":
			managerController.displayManagerReimbursements(request, response);
			break;
			
		case "/ExpenseReimbursementSystem/api/EmployeesDetails":
			employeeController.getEmployeeName(request, response);
			break;
			
		case "/ExpenseReimbursementSystem/api/ApproveReimbursement":
			employeeController.approveReimbursement(request, response);
			break;
			
		case "/ExpenseReimbursementSystem/api/DenyReimbursement":
			employeeController.denyReimbursement(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/AllPendingReimbursements":
			managerController.AllPendingReimbursements(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/AllApprovedReimbursements":
			managerController.AllApprovedReimbursements(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/AllDeniedReimbursements":
			managerController.AllDeniedReimbursements(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/AllReimbursements":
			managerController.AllReimbursements(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getReimbursementTypes":
			rTypeController.getAllTypes(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/submitReimbursement":
			rTypeController.submitReimbursement(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getManagerApproveStats":
			statsController.getManagerApproveStats(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getEmployeeRequestsStats":
			statsController.getEmployeeRequestStats(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/getAllEmployees":
			statsController.getAllEmployees(request, response);
			break;
		case "/ExpenseReimbursementSystem/api/ApproveDeny":
			statsController.approveDeny(request, response);
			break;

		
		
		default:
			response.getWriter().append("Your request uri did not match anything");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
