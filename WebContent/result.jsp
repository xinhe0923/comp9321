<%@ page import="dto.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>


<%
	String loginBool = (String) session.getAttribute("login");
	List<Item> elements = (List<Item>) request.getAttribute("elements");
	int totalPage = (int) Math.ceil((double) elements.size() / 10);
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap Core CSS -->
<jsp:include page="headerjsp_1.jsp" />


<title>The Book Store - Search Results</title>
</head>
<body>








	<!-- contrainer -->
	<div class="container">
		<!-- contrainer -->

		<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->
		<%
			if (loginBool == "true") {
		%>
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />
		<%
			} else {
		%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
		<%
			}
		%>


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
		<form action="do" method="get">
			

				   
					<table class="table table-inverse">
						<col width="5%" />
						<col width="60%" />
						<col width="25%" />
						<col width="5%" />
						<col width="5%" />
						<thead>
							<tr>
								<th>#</th>
								<th>Title</th>
								<th>Author/Editor</th>
								<th>Price</th>
								<th>Quantity</th>
							</tr>
						</thead>
						<tbody>

							<%
								for (Item element : elements) {
							%>
							<tr class="itemToShow">
								<td><img src="<%=element.getImageURL()%>"
									alt="Responsive image" class="autoResizeImage"></td>
								<td><a
									href="control?action=showDetail&id=<%=element.getId()%>"> <%=element.getTitle()%></a></td>
								<td><%=element.getAuthors().toString()%></td>
								<td><%=element.getPrice()%></td>
								<td><%=element.getQuantity()%></td>

							</tr>
							<%
								}
							%>

						</tbody>
					</table>

				
			


		</form>


		<div class="pager">
			<div class="">
				<a id="prev" class="pull-left btn btn-success page-nav"
					data-action="prev">Prev</a> <a id="next"
					class="pull-right btn btn-success page-nav" data-action="next">Next</a>
			</div>
			<div class="">
				<select id="page-select" class="form-control">
					<%
						for (int i = 0; i < totalPage; i++) {
					%>
					<option value="<%=i%>" data-index="<%=i%>">page
						<%=(i + 1)%> out of
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


		<!-- container_div starting-->
	</div>
	<!-- container_div ending-->





</body>
</html>

<script>
	var totalPage =
<%=totalPage%>
	;

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
		if (totalPage == 1) {
			$('#page-select').css('visibility', 'hidden');
		}
		if (currentIndex == 0) {
			$('#prev').css('visibility', 'hidden');
		} else {
			$('#prev').css('visibility', 'visible');
		}
		if (currentIndex == totalPage - 1) {
			$('#next').css('visibility', 'hidden');
		} else {
			$('#next').css('visibility', 'visible');
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

