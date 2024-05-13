package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeletebyId extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext ctx = getServletContext();
		String driver = ctx.getInitParameter("dbDriver");
		String user = ctx.getInitParameter("dbUser");
		String url = ctx.getInitParameter("dbUrl");
		String pass = ctx.getInitParameter("dbPass");
		
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			String query = "Delete from Employees where EID = ?";
			
            PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, request.getParameter("eId"));
			
			ps.executeUpdate();
			response.sendRedirect("EmployeeList.jsp");
			conn.close();
		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
