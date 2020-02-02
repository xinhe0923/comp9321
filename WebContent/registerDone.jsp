<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String loginBool = (String) session.getAttribute("login");
%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap Core CSS -->
	<jsp:include page="headerjsp_1.jsp" />
	
<title>The Book Store - Email Confirmed!</title>
</head>
<body>
	
	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->

	
	

	
	<div class="container" role="main">
		<div class="col-md-10 col-md-offset-1" align="center">
		    	<% if (loginBool == "true") {%>	
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
	<% } %>
			
		    <font color="white"> <h1>Registration</h1><font>
			<font color="white"> <h2>Please check your email to verify your account!</h2></font>
			<a class="btn btn-primary" href="home.jsp" title=""> <span
				class="icon"></span> <span class="text">Home</span>
			</a>
		</div>
	</div>








</body>
</html>