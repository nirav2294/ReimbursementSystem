package dev.patel.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.patel.entities.Manager;
import dev.patel.services.ManagerService;
import dev.patel.services.ManagerServiceImpl;

public class ManagerController {

	private ManagerService managerService = new ManagerServiceImpl();
	Gson gson = new Gson();

	public void getManagerName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			List<Manager> managers = new ArrayList<Manager>();
			PrintWriter pw = response.getWriter();
			String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
			for(int i=2; i<body.length()-2; i+=2) {
				char temp = body.charAt(i);
				int id = Character.getNumericValue(temp);

				managers.add(managerService.getManagerById(id));
				
			} 
			String json = gson.toJson(managers);
			System.out.println(json);
			pw.append(json);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void getAllManagers(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			List<Manager> managers = managerService.getAllManagers();
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(managers);
			System.out.println(json);
			pw.append(json);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
