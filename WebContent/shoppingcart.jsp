<%@ page import="dto.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="service.CartService" %>
<%@ page import="dto.User" %>
<%@ page import="dto.Cart" %>


<%
	String loginBool = (String) session.getAttribute("login");
	User user = (User) session.getAttribute("user");
	CartService cartService = new CartService();
	List<Cart> elements = cartService.getExistedCart(user.getId());
	int totalPage = (int) Math.ceil((elements.size() / 10) + 1);
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap Core CSS -->
	
	<jsp:include page="headerjsp_1.jsp" />
	
	
<style>
.dblp-item {
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

.btn {
   
    font-size: 20px;
 
 
    color:white
}

</style>
<title>The Book Store - My Shopping Cart</title>

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



	<%
		if (elements == null || elements.isEmpty()) {
	%>


		<div class="row" style="margin: 40px" align="center">
    		<h1>Shopping Cart is Empty!</h1>
		</div>

	<%
		} else {
	%>


	<form id="cartForm" action="control"   method="get">


	
		<%--<input type="hidden" name="servlet" value="onDel" />--%>
		 <table class="table table-inverse">
					<col width="5%" />
					<col width="40%" />
					<col width="20%" />
					<col width="10%" />
			<col width="10%" />
					<col width="15%" />
					<thead>
						<tr>
							<th>#</th>
							<th>Title</th>
							<th>Author/Editor</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Quantity Left</th>
							<th>Seller</th>
						</tr>
					</thead>
					<tbody>
			<%
				for (Cart element : elements) {
			%>
			<tr class="dblp-item">
				<td><input type="checkbox" name="pick" value="<%=element.getId()%>"></td>
				<td><a href="control?action=showDetail&id=<%=element.getItem().getId()%>"> <%=element.getItem().getTitle()%></a></td>
				<td><%=element.getItem().getAuthors().toString()%></td>
				<td><%=element.getItem().getPrice()%></td>
				<td><%=element.getCount()%></td>
				<td><%=element.getItem().getQuantity()%></td>
				<td><%=element.getItem().getSeller().getUsername()%></td>
			</tr>
		
			<%
				}
			%>
			
				</tbody>
			</table>

	
	<div class="row" style="padding-top: 40px">
        <div class="col-md-6 col-md-offset-3" align="center">
                <button id="clearCart" class="btn btn-link" type="submit" name="action" value="rmshoppingcart"><span class="glyphicon glyphicon glyphicon-trash"></span>Remove </button>
                <button id="checkoutCart" class="btn btn-link" type="submit" name="action" value="makeorder"><span class="glyphicon glyphicon-credit-card"></span>Payment </button>
		</div>
	</div>

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

	<%
		}
	%>


	</div>






	

</body>
</html>

<script>
	var totalPage = <%=totalPage%>;

	function showCurrentPage() {
		var dblps = $('.dblp-item');
		var select = $('#page-select');
		var currentIndex = select.val();
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
		dblps.hide();
		var start = currentIndex * 10;
		var i = 0;
		for (i = start; i < start + 10; i++) {
			var el = $(dblps[i]);
			el.show();
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

