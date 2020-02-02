<%@ page import="dto.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="service.ItemService"%>
<%@ page import="dto.OrderItem" %>


<%
	String adminLogin = (String) session.getAttribute("adminlogin");
	String loginBool = (String) session.getAttribute("login");
	String username = (String) request.getAttribute("username");
	List<OrderItem> elements = (List<OrderItem>) request.getAttribute("elements");
	int totalPage = (int) Math.ceil((double)elements.size() / 10);
	//get username
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

<title>The Book Store - Admin - Items Bought</title>
</head>
<body>






	<!-- after login -->
	<% if (adminLogin == "true") {%>

	<div class="container " role="main">

		<%
		if (elements.isEmpty()) {
	%>
		<% if (adminLogin == "true") {%>
	<!-- after login -->
	<jsp:include page="adminheader.jsp" />
	<%} else {%>
	<!-- before login -->
	<jsp:include page="adminloginheader.jsp" />
	<% } %>
		<div class="row" style="margin: 40px" align="center">
			<div class="col-md-8 col-md-offset-1">
				<h1>Sorry, this user hasn't bought anything!</h1>
			</div>
		</div>

		<%
			} else {
		%>
			<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->

	<% if (adminLogin == "true") {%>
	<!-- after login -->
	<jsp:include page="adminheader.jsp" />
	<%} else {%>
	<!-- before login -->
	<jsp:include page="adminloginheader.jsp" />
	<% } %>


		<!--  Search result: Title, Author,  Price, Quantity, Seller, Action(Remove) -->

		<form action="do" method="get">
			
				
					<h3 class="panel-title"><%=username%></h3>
				<
				

					<input type="hidden" name="servlet" value="onAdd" />
					<table class="table table-inverse">
						<thead>
							<tr>
								<th>Pub-Title</th>
								<th>Timestamp</th>
								<th>Price</th>
								<th>Seller</th>
							</tr>
						</thead>
						<tbody>

							<%
					for (OrderItem element : elements) {
				%>
							<tr class="itemToShow">
								<!--  please link detail to 'adminDetail.jsp' -->
								<!-- linke to adminDetail.jsp -->
								<td><a
									href="control?action=adminShowDetail&id=<%=element.getItem_id()%>"> <%=element.getItem().getTitle()%></a></td>
								<td><%=element.getOrder().getCreated()%></td>
								<td><%=element.getItem().getPrice()%></td>
								<td><%=element.getItem().getSeller().getUsername()%></td>
							</tr>
							<%
				}
			%>

						</tbody>
					</table>

				
			


		</form>


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

	</div>

	<% } else { %>

	<div class="container theme-showcase" role="main">
		<div class="col-md-10 col-md-offset-1" align="center">
			<h1><span class="glyphicon glyphicon-exclamation-sign"></span> No authority: Please log in!</h1>
			<a class="btn btn-primary" href="admin.jsp" title=""> <span
				class="icon"></span> <span class="text">Admin Home</span>
			</a>
		</div>
	</div>



	<% } %>








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


