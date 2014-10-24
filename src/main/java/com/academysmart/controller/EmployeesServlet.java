package com.academysmart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.repository.EmployeeRepositorySingleton;

@WebServlet("/MyServlet.html")
public class EmployeesServlet extends HttpServlet {
	//TODO implement logic to process data that client sent to server with POST method.
	//It could include adding employee to repository,
	//validating email, redirecting client to a page where employee list is displayed etc.

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//TODO implement logic to process GET requests
		
		getServletContext().getRequestDispatcher
				("/employee.jsp").forward(request, response);		
		request.setAttribute("employees", EmployeeRepositorySingleton.
				getRepository().getAllEmployees());				
	}

		
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			EmployeeRepositorySingleton.getRepository().addEmployee(
					request.getParameter("fname"),
					request.getParameter("lname"),
					request.getParameter("email"));
		}catch (IncorrectEmailException e) {					
			//e.printStackTrace();			
			request.setAttribute("errMsg", e);
		}catch (com.academysmart.exception.ServletException e) {					
			request.setAttribute("errMsg", e);
		}
		
		doGet(request, response);
	}
}