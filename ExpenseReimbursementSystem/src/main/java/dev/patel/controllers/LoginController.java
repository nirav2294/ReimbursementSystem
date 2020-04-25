package dev.patel.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dev.patel.entities.Employee;
import dev.patel.entities.Manager;
import dev.patel.exceptions.ManagerNotFoundException;
import dev.patel.exceptions.UserNotFoundException;
import dev.patel.services.EmployeeService;
import dev.patel.services.EmployeeServiceImpl;
import dev.patel.services.ManagerService;
import dev.patel.services.ManagerServiceImpl;

public class LoginController {

	private EmployeeService emplService = new EmployeeServiceImpl();
	private ManagerService managerService = new ManagerServiceImpl();

	Gson gson = new Gson();

	public void loginEmployee(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Employee employee = gson.fromJson(body, Employee.class);
		Employee user = new Employee();
		PrintWriter pw = response.getWriter();
		try {

			user = emplService.validateEmployee(employee.getUserName(), employee.getPassword());
			if (user.getFirstName() != null) {
				String id = request.getParameter("username");
				HttpSession sess = request.getSession();
				sess.setAttribute("username", id);
				System.out.println(id);
				String json = gson.toJson(user);
				System.out.println(json);
				pw.append(json);

			}

		} catch (UserNotFoundException e) {
			System.out.println(e);
			if (user.getFirstName() == null) {
				String json = gson.toJson(user);
				System.out.println(json);
				pw.append(json);
			}
		}

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();

			if (session != null) {

				session.removeAttribute("username");
				session.invalidate();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void managerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Manager manager = gson.fromJson(body, Manager.class);
		Manager user = new Manager();
		PrintWriter pw = response.getWriter();
		try {

			user = managerService.validateManager(manager.getUserName(), manager.getPassword());
			if (user.getFirstName() != null) {
				String id = request.getParameter("username");
				HttpSession sess = request.getSession();
				sess.setAttribute("username", id);
				System.out.println(id);
				String json = gson.toJson(user);
				System.out.println(json);
				pw.append(json);

			}

		} catch (ManagerNotFoundException e) {
			System.out.println(e);
			if (user.getFirstName() == null) {
				String json = gson.toJson(user);
				System.out.println(json);
				pw.append(json);
			}
		}

	}

}
