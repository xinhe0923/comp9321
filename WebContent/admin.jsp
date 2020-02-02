<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String loginBool = (String) session.getAttribute("login");
	String adminLogin = (String) session.getAttribute("adminlogin");
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />
<script src="JS/jquery.js"></script>
<script src="JS/register.js"></script>
<style type='text/css'>
.btn {
    
    font-size: 20px;
    
      
    color:white
}
</style>

<title>The Book Store - Admin - Sign In</title>
</head>
<body>



	
	
	<!-- after log in - same account can't see log in page again -->
 <% if (adminLogin == "true") {%>
    <!-- after login -->
        <div class="container theme-showcase" role="main">
            <div class="row" style="margin: 40px" align="center">
            	<div class="col-md-8 col-md-offset-1">
                	<h1><span class="glyphicon glyphicon-exclamation-sign"></span> Already Log in!</h1>
                </div>
            </div>
        </div>

<% } else {%>



	<!-- contrainer -->
<div class="container">
	<!-- contrainer -->
	
		<% if (adminLogin == "true") {%>	
		<!-- after login -->
		<jsp:include page="adminheader.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="adminloginheader.jsp" />
	<% } %>
	
	
		<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->
     


		<form id="adminloginform" action="control" method="post" class="form-horizontal">
		

			<div class="container">
				<div class="form-group form-group-md">
					<label class="col-md-4 control-label">Username</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="username" name="username"
							placeholder="...">
					</div>
				</div>


				<div class="form-group form-group-md">
					<label class="col-md-4 control-label">Password</label>
					<div class="col-md-3">
						<input class="form-control" type="password" id="password" name="password"
							placeholder="...">
					</div>
				</div>
				
				<div class="row" style="padding-top: 40px">
					<div class="col-md-3 col-md-offset-4" align="center">
						<input type="hidden" name="action" value="adminlogin"/>
						<button type="submit" class="btn btn-link">Log In</button>
					</div>
				</div>

			</div>

		</form>

	</div>

<% } %>



</body>
</html>