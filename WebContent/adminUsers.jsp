<%@ page import="dto.Item"%>
<%@ page import="dto.User"%>
<%@ page import="service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>


<%
	String loginBool = (String) session.getAttribute("login");
	String adminLogin = (String) session.getAttribute("adminlogin");
	UserService userService = new UserService();
	List<User> users = (List<User>) userService.getAllUsers();   //all user names
	int totalPage = (int) Math.ceil((double)users.size() / 10);
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />

<style>
.itemToShow {
	display: none;
}

#page-select {
	display: inline-block;
	width: 60%;
}

input[type="checkbox"] {
  display: block;
  margin: auto;
}
</style>


<title>The Book Store - Admin - Check on users</title>
</head>
<body>

	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->





	<div class="container theme-showcase" role="main">

	<%
	if (adminLogin == "true") {
		if (users.isEmpty()) {
	%>
		<div class="row" style="margin: 40px" align="center">
			<div class="col-md-8 col-md-offset-1">
				<h1>Sorry, no users!</h1>
			</div>
		</div>

		<%
			} else {
		%>
                      
	<% if (adminLogin == "true") {%>	
		<!-- after login -->
		<jsp:include page="adminheader.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="adminloginheader.jsp" />
	<% } %>
		
	
			
				<table class="table table-inverse">
					<thead>
						<tr>
							<th>Username</th>
							<th>Items Bought</th>
							<th>Items Removed</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

				<%
					for (User user : users) {
				%>
				<form action="control" method="get">
				<tr class="itemToShow">
					<td><%=user.getUsername()%></td>
					<td><a class="btn btn-xs btn-danger" a href="control?action=checkbought&id=<%=user.getId()%>"> <span class="icon"></span> <span class="text">Bought</span></a></td>
					<td><a class="btn btn-xs btn-danger" a href="control?action=checkremoved&id=<%=user.getId()%>"> <span class="icon"></span> <span class="text">Removed</span></a></td>
					<input type="hidden" name="id" value="<%=user.getId()%>">
					<% if (user.isBan()){%>
					<input type="hidden" name="action" value="unbanuser">
					<td><button type="submit" class="btn btn-xs btn-danger">Unban</button></td>
					<%} else {%>
                    <input type="hidden" name="action" value="banuser">
                    <td><button type="submit" class="btn btn-xs btn-danger">&nbsp&nbspBan&nbsp&nbsp&nbsp</button></td>
					<% } %>
				</tr>
				</form>
			<%
				}
			%>

					</tbody>
				</table>
				
			</div>
		


	
		
	<div class="pager">
		<div class="">
			<a id="prev" class="pull-left btn btn-success page-nav" data-action="prev">Prev</a>
			<a id="next" class="pull-right btn btn-success page-nav" data-action="next">Next</a>
		</div>
		<div class="">
			<select id="page-select" class="form-control">
				<%
					for (int i = 0; i < totalPage; i++) {
				%>
				<option value="<%=i%>" data-index="<%=i%>">page
					<%=(i+1)%> out of
					<%=totalPage%></option>
				<%
					}
				%>
			</select>
		</div>
	</div>

	<% } %>
	
	
	<%} else {%>
    <!-- before login -->
        <div class="container theme-showcase" role="main">
            <div class="row" style="margin: 40px" align="center">
            	<div class="col-md-8 col-md-offset-1">
                	<h1><span class="glyphicon glyphicon-exclamation-sign"></span> No Authority: Please Log in!</h1>
                	<a class="btn btn-primary" href="admin.jsp" title=""> <span
					class="icon"></span> <span class="text">Admin Home</span>
					</a>
                </div>
            </div>
        </div>
    <% } %>


	</div>






</body>
</html>

<script>
	var totalPage = <%=totalPage%>;

	function showCurrentPage() {
		var itemToShow = $('.itemToShow');
		var select = $('#page-select');
		var currentIndex = select.val();

		itemToShow.hide();
		var start = currentIndex * 10;
		var i = 0;
		for (i = start; i < start + 10; i++) {
			var el = $(itemToShow[i]);
			el.show();
		}
		if (totalPage == 1){
			$('#page-select').css('visibility','hidden');
		}
		if (currentIndex == 0){
			$('#prev').css('visibility','hidden');
		}
		else{
			$('#prev').css('visibility','visible');
		}
		if(currentIndex == totalPage - 1){
			$('#next').css('visibility','hidden');
		}
		else{
			$('#next').css('visibility','visible');
		}
	}

	$('#page-select').change(function(e) {
		showCurrentPage();
	});

	$('.page-nav').click(function(e) {
		var btn = $(e.target);
		var action = btn.data('action');
		var currentIndex = parseInt($('#page-select').val());

		if (action == "next") {
			if (currentIndex < totalPage - 1) {
				$('#page-select').val(currentIndex + 1);
				$('#page-select').change();
			}
		} else if (currentIndex > 0) {
			$('#page-select').val(currentIndex - 1);
			$('#page-select').change();
		}
	});

	showCurrentPage();
</script>
