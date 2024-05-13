<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>		
<script src="https://code.jquery.com/jquery-3.6.0.js"integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title></title>

<style>
   
   #table{
   
   font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
   
   }
   
   #table td, #table th {
  border: 1px solid #ddd;
  padding: 8px;
}
   #table tr:nth-child(even){background-color: lightgrey;}
   #table tr:hover {background-color: grey;}
   
   #table th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: black;
  color: white;
}
   </style>
 

</head>
<body>

<div class="container-fluid">
<nav class="navbar navbar-expand-lg navbar-light bg-light" id="nav">
  <a class="navbar-brand" href="Home.html">Home</a>
 

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li>
        <a class="nav-link" href="#">Employee List <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Home.html">Add New Employee</a>
      </li>
      
      
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
  
</nav>
<div style="background-color: grey; height: 80px; align-content: center;">

<div class="row">
<div class="col-md-3"><i class="fa fa-user" style="padding: 8px"></i>Employee Management System</div>
<div class="col-md-3"></div>
<div class="col-md-3"></div>
<div class="col-md-3" align="right">
<p id="p1"></p>
<script>
	var date = new Date();
	document.getElementById("p1").innerHTML = date;
	
</script>
</div>
</div>

</div>

<div>
<div class="row">
<div class="col-md-6">
<a href="Delete.html">Delete Employee Details</a><br>
<a href="UpdateEmp.html">Update Employee Details</a><br>
<a href="Show.html">Show Employee Details</a><br>
<a href="SalaryRange.html">Fetch by Salary Range</a>
<br><br><br>

 <a href="More.jsp">More</a> 

</div>
<div class="col-md-6">




<table id="table">

<tr>
<th>Employee Id</th>
<th>First Name</th>
<th>Email</th>
 </tr>
    
   <tr>
   <% 
   ServletContext ctx = getServletContext();
		String driver = ctx.getInitParameter("dbDriver");
		String user = ctx.getInitParameter("dbUser");
		String url = ctx.getInitParameter("dbUrl");
		String pass = ctx.getInitParameter("dbPass");
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "Select * from Employees";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while(rs.next()){ %>
	        <tr>
		        <td><%= rs.getString(1) %></td>
		        <td><%= rs.getString(2) %></td>
		        <td><%= rs.getString(4) %></td>
		        
	        </tr>	
	        
	 <% }
        
        
    }catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    %>
  
</table>
</div>
</div>
</div>


</body>
</html>


