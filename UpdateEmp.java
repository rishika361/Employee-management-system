package com.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateEmp extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "java",
                    "java");

            String empIdParam = request.getParameter("Emp_Id");
            String deptIdParam = request.getParameter("Dept_Id");
            String salaryParam = request.getParameter("Salary");

            if (empIdParam != null && deptIdParam != null && salaryParam != null) {
                int EId = Integer.parseInt(empIdParam);
                int DptNum = Integer.parseInt(deptIdParam);
                int ESalary = Integer.parseInt(salaryParam);

                String query = "UPDATE Employees SET FName =?, LName =?, Email =?, DName =?, DId =?, Designation =?, Sal =?, Address=? WHERE EId =?";
                PreparedStatement ps = conn.prepareStatement(query);

                String FName = request.getParameter("First_Name");
                String LName = request.getParameter("Last_Name");
                String EEmail = request.getParameter("Email");
                String DptName = request.getParameter("Dept_Name");
                String Desgin = request.getParameter("Designation");
                String EAdd = request.getParameter("Address");

                if (EId != 0 && FName != null && LName != null && EEmail != null && DptName != null && DptNum != 0
                        && Desgin != null && ESalary != 0 && EAdd != null) {

                    ps.setString(1, FName);
                    ps.setString(2, LName);
                    ps.setString(3, EEmail);
                    ps.setString(4, DptName);
                    ps.setInt(5, DptNum);
                    ps.setString(6, Desgin);
                    ps.setInt(7, ESalary);
                    ps.setString(8, EAdd);
                    ps.setInt(9, EId);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                    	response.sendRedirect("EmployeeList.jsp");
                    } else {
                        out.println("<h1>No records updated</h1>");
                    }
                } else {
                    out.println("<h1>Invalid input data</h1>");
                }

                ps.close();
            } else {
                out.println("<h1>Invalid input data: One or more parameters are missing</h1>");
            }

            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
 
		
	}

}
