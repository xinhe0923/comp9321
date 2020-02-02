<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />
<script src="JS/jquery.js"></script>
<script src="JS/register.js"></script>


<title>The Book Store - Admin - Error</title>
</head>
<body>

	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->


			
	

	

	<div class="container " role="main">
			<!-- before login -->
		<jsp:include page="adminloginheader.jsp" />


	
	
			<font color="white"><h1 class="text-center"> Wrong Password!</h1></font>
				
					
					


		<form id="adminloginform" action="control" method="post" class="form-horizontal">

			<div class="container">
				<input type="hidden" name="action" value="login">
				<div class="form-group form-group-md">
					<label class="col-md-4 control-label" for="InputUsername">Username</label>
					<div class="col-md-3">
						<input class="form-control" type="text" id="InputUsername" name="username"
							placeholder="...">
					</div>
				</div>


				<div class="form-group form-group-md">
					<label class="col-md-4 control-label" for="InputPassword">Password</label>
					<div class="col-md-3">
						<input class="form-control" type="password" id="InputPassword" name="password"
							placeholder="...">
					</div>
				</div>
				
				<div class="row" style="padding-top: 40px">
					<div class="col-md-3 col-md-offset-4" align="center">
						<button type="submit" class="btn btn-primary">Log In</button>
					</div>
				</div>

			</div>

		</form>

	</div>






</body>
</html>