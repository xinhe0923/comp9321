<%@ page import="dto.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="service.ItemService" %>

	
<%
	String adminLogin = (String) session.getAttribute("adminlogin");
	String loginBool = (String) session.getAttribute("login");
	List<Item> elements = (List<Item>) request.getAttribute("elements");
	int totalPage = (int) Math.ceil((double)elements.size() / 10);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />



<title>The Book Store - Admin - Search Results</title>
</head>
<body>




	<!-- after login -->
	<% if (adminLogin == "true") {%>
	<div class="container " role="main">
   	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->

	<% if (adminLogin == "true") {%>	
		<!-- after login -->
		<jsp:include page="adminheader.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="adminloginheader.jsp" />
	<% } %>

		
		
		
	<%
		if (elements.isEmpty()) {
	%>
		<div class="row" style="margin: 40px" align="center">
			<div class="col-md-8 col-md-offset-1">
				<h1>Sorry, no matching data found!</h1>
			</div>
		</div>

		<%
			} else {
		%>
		

		<!--  Search result: Title, Author,  Price, Quantity, Seller, Action(Remove) -->


		

			
			
				<input type="hidden" name="servlet" value="onAdd" />
				<table class="table table-inverse">
					<col width="5%" />
					<col width="55%" />
					<col width="20%" />
					<col width="5%" />
					<col width="5%" />
					<col width="10%" />
					<thead>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Author/Editor</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>

				<%
					for (Item element : elements) {
				%>
				<form action="control" method="get">
				<tr class="itemToShow">
				<!--  please link detail to 'adminDetail.jsp' -->
					<td><img
								src="<%=element.getImageURL()%>"
								alt="Responsive image" class="autoResizeImage"> </td>
					<td><a href="control?action=adminShowDetail&id=<%=element.getId()%>"> <%=element.getTitle()%></a></td>
					<td><%=element.getAuthors().toString()%></td>
					<td><%=element.getPrice()%></td>
					<td><%=element.getQuantity()%></td>
					<input type="hidden" name="id" value="<%=element.getId()%>">
					<%if (element.isBan()){%>
					<td><button class="btn btn-xs btn-success" type="submit" name="action" value="unbanitem">Restore</button></td>
					<%}else {%>
					<td><button class="btn btn-xs btn-danger" type="submit" name="action" value="banitem">Remove</button></td>
					<%}%>
				</tr>
				</form>
			<%}%>

					</tbody>
				</table>
				
		


	
		
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
			<h1> <span class="glyphicon glyphicon-exclamation-sign"></span> No authority: Please log in!</h1>
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
