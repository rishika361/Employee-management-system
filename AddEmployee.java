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


public class AddEmployee extends HttpServlet {
	
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

            String query = "insert into Employees values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            int eId = Integer.parseInt(request.getParameter("eId"));
            ps.setInt(1, eId);
            ps.setString(2, request.getParameter("fname"));
            ps.setString(3, request.getParameter("lname"));
            ps.setString(4, request.getParameter("email"));
            ps.setString(5, request.getParameter("dname"));
            String dIdParam = request.getParameter("dId");
            int dId = 0; 
            if (dIdParam != null && !dIdParam.trim().isEmpty()) {
                dId = Integer.parseInt(dIdParam.trim());
            }
            ps.setInt(6, dId);

            ps.setString(7, request.getParameter("designation"));
            ps.setInt(8, Integer.parseInt(request.getParameter("sal")));
            ps.setString(9, request.getParameter("address"));

            ps.executeUpdate();
            response.sendRedirect("Home.html");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException e) {
            // Handle NumberFormatException
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data: Number format error");
        }


		
		
	}

}
