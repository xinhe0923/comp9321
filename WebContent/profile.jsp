<%@ page import="dto.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String loginBool = (String) session.getAttribute("login");
    User user = (User) session.getAttribute("user");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />
<script src="JS/jquery.js"></script>
<script src="JS/profile.js"></script>


<title>The Book Store - My Profile</title>
</head>
<body>




<!-- contrainer -->
<div class="container">
	<!-- contrainer -->
		<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->
	<% if (loginBool == "true") {%>	
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
	<% } %>


    <% if (loginBool == "true") {%>
	

		<form id="profileform" action="control" method="post">

			<div class="container">

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Username</label>
                        <%=user.getUsername()%>
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Password</label> <input type="password" id="password"
							name="password" class="form-control" placeholder="password...">
						<div></div>
					</div>
				</div>

                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <label for="#">Confirm Password</label>
                        <input type="password" id="password2" name="#" class="form-control" placeholder="password...">
                        <div></div>
                    </div>
                </div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">E-mail</label>
                        <input type="text" id="email" name="email" class="form-control" value="<%=user.getEmail()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Nickname</label> <input type="text" id="nickname" name="nickname"
							class="form-control" value="<%=user.getNickname()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">First Name</label> <input type="text" id="firstname"
							name="firstname" class="form-control" value="<%=user.getFirstname()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Last Name</label> <input type="text" id="lastname"
							name="lastname" class="form-control" value="<%=user.getLastname()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Year of Birth</label> <input type="text" id="yob"
							name="birthyear" class="form-control" value="<%=user.getBirthyear()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Address</label> <input type="text" id="address" name="address"
							class="form-control" value="<%=user.getAddress()%>">
						<div></div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<label for="#">Credit Card Number</label> <input type="text"
							id="creditcard" name="creditcard" class="form-control"
							value="<%=user.getCreditcard()%>">
						<div></div>
					</div>
				</div>

				<div class="row" style="padding-top: 40px">
					<div class="col-md-4 col-md-offset-4" align="center">
                        <input type="hidden" name="action" value="editprofile">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
						<button type="submit" class="btn btn-white">Edit</button>
					</div>
				</div>
			</div>

		</form>
	<!-- container_div starting-->
</div>
<!-- container_div ending-->

    <%} else {%>
    <!-- before login -->
    <div class="container theme-showcase" role="main">
        <div class="row" style="margin: 40px" align="center">
            <div class="col-md-8 col-md-offset-1">
                <h1>Please Login!</h1>
            </div>
        </div>
    </div>
    <% } %>









	<!-- Bootstrap Core JavaScript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="CSS/bootstrap.js"></script>




</body>
</html>