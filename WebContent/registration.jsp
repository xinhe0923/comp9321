<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String loginBool = (String) session.getAttribute("login");
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />
<script src="JS/jquery.js"></script>
<script src="JS/register.js"></script>


<title>The Book Store - Registration</title>
<jsp:include page="headerjsp_1.jsp" />
</head>
<body>





<!-- contrainer -->
<div class="container">
<!-- contrainer -->
      	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->

	<!-- before login -->
	<% if (loginBool == "true") {%>	
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
	<% } %>

        <font color="white"><h2 class="text-center">Registration</h2></font>
		<form id="registerform" action="control" method="post" class="form-horizontal">

			<div class="container">
				<input type="hidden" name="action" value="registration">
				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Username</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="username" name="username"
							placeholder="Input...">
					</div>
				</div>


				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">New Password</label>
					<div class="col-md-3">
						<input class="form-control" type="password" id="password" name="password"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Confirm Password</label>
					<div class="col-md-3">
						<input class="form-control" type="password" id="password2" name="password2"
							placeholder="Input...">
					</div>
				</div>


				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">E-mail</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="email" name="email"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">First Name</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="firstname" name="firstname"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Last Name</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="lastname" name="lastname"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Nickname</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="nickname" name="nickname"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Year of Birth</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="yob" name="birthyear"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Address</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="address" name="address"
							placeholder="Input...">
					</div>
				</div>

				<div class="form-group form-group-md">
					<label class="col-md-5 control-label">Credit Card Number</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="creditcard" name="creditcard"
							placeholder="Input...">
					</div>
				</div>

				<div class="row" style="padding-top: 40px">
					<div class="col-md-3 col-md-offset-4" align="center">
						<button type="submit" class="btn btn-white">Submit</button>
					</div>
				</div>


			</div>

		</form>
		
<!-- container_div starting-->
</div>
<!-- container_div ending-->









</body>
</html>