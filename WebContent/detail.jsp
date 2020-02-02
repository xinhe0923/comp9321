<%@ page import="dto.Item"%>
<%@ page import="service.ItemService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	String loginBool = (String) session.getAttribute("login");
	Item detialItem = (Item) request.getAttribute("detail");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />

<title>The Book Store - Details of the book</title>
</head>
<body>





	<!-- contrainer -->
	<div class="container">
		<!-- contrainer -->
		<!-- check whether the user have log in and give the different title starting -->

		<% if (loginBool == "true") {%>
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />
		<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
		<% } %>
		<!-- check whether the user have log in and give the different title  ending-->


		<div class="row">

			<div class="col-md-3">
			<table class="table table-inverse">
                <td><img src="C:/web eclipse/eclipse/workplace/comp9321ass2_final/WebContent/css/image/play1.jpg"  alt="no_picture_upload" /></td>
              
                </table>
				

			</div>


			<div class="col-mid-9 col-md-offset-3">




				<table class="table table-inverse">
					<col width="30%" />
					<col width="70%" />
					<tr>
						<td>Title</td>
						<td><%=detialItem.getTitle()%></td>
					</tr>
					<tr>
						<td>Author</td>
						<td><%=detialItem.getAuthors()%></td>
					</tr>

					<tr>
						<td>Publication Type</td>
						<td><%=detialItem.getType()%></td>
					</tr>
					<tr>
						<td>Year</td>
						<td><%=detialItem.getPublication_date()%></td>
					</tr>
					<tr>
						<td>Price</td>
						<td><%=detialItem.getPrice()%></td>
					</tr>
					<tr>
						<td>Quantity</td>
						<td><%=detialItem.getQuantity()%></td>
					</tr>
					<tr>
						<td>Seller</td>
						<td><%=detialItem.getSeller().getUsername()%></td>
					</tr>
				</table>



			</div>
		</div>

		<%
		if (loginBool == "true") {
	%>
		<div class="row" style="padding-top: 40px">
			<div class="col-md-3 col-md-offset-4" align="center">
				<form action="control" method="get">
					<input type="hidden" name="itemId" value="<%=detialItem.getId()%>">
					<input type="hidden" name="action" value="addtocart" />
					<button class="btn btn-primary" type="submit">Add to Cart</button>
				</form>
			</div>
		</div>
		
		<div class="row" style="padding-top: 40px">
			<div class="col-md-3 col-md-offset-4" align="center">
				<form action="control" method="get">
					<input type="hidden" name="itemId" value="<%=detialItem.getId()%>">
					<input type="hidden" name="action" value="addtowishlist" />
					<button class="btn btn-primary" type="submit">Add to Wishlist</button>
				</form>
			</div>
		</div>
		<% } %>


		<!-- container_div starting-->
	</div>
	<!-- container_div ending-->











</body>
</html>