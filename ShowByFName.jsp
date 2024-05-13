<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>

    <style>
        table {
            width: 80%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<%@ page errorPage="errorpage.jsp"%>
    <table align="center">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Department</th>
            <th>Salary</th>
        </tr>

        <%
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "java", "java");
            String sql = "select * from employees where fName = ?";
            ps = con.prepareStatement(sql);
            String Name = request.getParameter("fName");
            ps.setString(1, Name);
            rs = ps.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
        %>
        <tr>
            <td><%=rs.getInt("EId")%></td>
            <td><%=rs.getString("FName")%></td>
            <td><%=rs.getString("LName")%></td>
            <td><%=rs.getString("DId")%></td>
            <td><%=rs.getDouble("Sal")%></td>
        </tr>
        <%
            }
            out.println("<tr><td colspan='5'>Total Employees: " + count + "</td></tr>");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
        
    </table>
    <br><br><br>
    <button class="btn btn-register" style="margin-left: 150px "><a href="Home.html" style="text-decoration: none;">Click to Render on Home Page</a></button>

</body>
</html>