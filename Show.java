package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Show extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "java", "java");
			int empId = Integer.parseInt(request.getParameter("empId"));
	     
			String query = "SELECT * FROM Employees WHERE EId=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, empId);
			ResultSet rs = statement.executeQuery();
		
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("table { border-collapse: collapse; width: 50%; margin: auto; }");
			out.println("th, td { padding: 10px; border-bottom: 1px solid #ddd; }");
			out.println("th { background-color: #f2f2f2; }");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			
			
			if (rs.next()) {
				out.println("<table>");
				out.println("<tr><th>Field</th><th>Value</th></tr>");
				
				out.println("<tr><td>Employee Id</td><td>" + rs.getInt("EId") + "</td></tr>");
				out.println("<tr><td>Employee Name</td><td>" + rs.getString("FName") + "</td></tr>");
				out.println("<tr><td>Email Id</td><td>" + rs.getString("Email") + "</td></tr>");
				out.println("<tr><td>Department Name</td><td>" + rs.getString("DNAME") + "</td></tr>");
				out.println("<tr><td>Department Id</td><td>" + rs.getInt("DId") + "</td></tr>");
				out.println("<tr><td>Designation</td><td>" + rs.getString("Designation") + "</td></tr>");
				out.println("<tr><td>Salary</td><td>" + rs.getInt("Sal") + "</td></tr>");
				out.println("<tr><td>Address</td><td>" + rs.getString("Address") + "</td></tr>");
				
				out.println("</table>");
				out.println("<br>");
	            
	            
	            out.println("<form action='Home.html'>");
	            out.println("<button type='submit' style='display: block; margin: auto;'>Go to Home Page</button>");
	            out.println("</form>");

			} else {
	    		out.println("<p>No employee found with Id: " + empId + "</p>");
	    	}
			
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
