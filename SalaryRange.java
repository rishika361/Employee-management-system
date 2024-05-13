package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SalaryRange extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        ServletContext ctx = getServletContext();
	        String driver = ctx.getInitParameter("dbDriver");
	        String user = ctx.getInitParameter("dbUser");
	        String url = ctx.getInitParameter("dbUrl");
	        String pass = ctx.getInitParameter("dbPass");

	        double minSalary = Double.parseDouble(request.getParameter("minS"));
	        double maxSalary = Double.parseDouble(request.getParameter("maxS"));

	        try {
	            Class.forName(driver);
	            Connection con = DriverManager.getConnection(url, user, pass);
	            String sql = "SELECT * FROM Employees WHERE sal BETWEEN ? AND ?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setDouble(1, minSalary);
	            ps.setDouble(2, maxSalary);

	            // Execute query
	            ResultSet rs = ps.executeQuery();

	            // Start generating HTML output
	            out.println("<!DOCTYPE html>");
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Employee Salary Range</title>");
	            out.println("<style>");
	            out.println("table { border-collapse: collapse; width: 100%; }");
	            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
	            out.println("th { background-color: black; color: white; }");
	            out.println("tr:nth-child(even) { background-color: lightgray; }");
	            out.println("tr:hover { background-color: gray; }");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");

	            out.println("<h2>Employees with salary between " + minSalary + " and " + maxSalary + ":</h2>");
	            out.println("<table>");
	            out.println("<tr><th>ID</th><th>Name</th><th>Salary</th></tr>");

	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt("eid") + "</td>");
	                out.println("<td>" + rs.getString("fname") + "</td>");
	                out.println("<td>" + rs.getDouble("sal") + "</td>");
	                out.println("</tr>");
	            }

	            out.println("</table>");
	            out.println("<br>");
	            
	            
	            out.println("<form action='Home.html'>");
	            out.println("<button type='submit'>Go to Home Page</button>");
	            out.println("</form>");

	            out.println("</body>");
	            out.println("</html>");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
