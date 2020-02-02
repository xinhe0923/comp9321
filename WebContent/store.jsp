<%@ page import="service.ItemService" %>
<%@ page import="dto.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
    String loginBool = (String) session.getAttribute("login");
    ItemService itemService = new ItemService();
    List<Item> itemList = new ArrayList<>();
    if (loginBool == "true"){
        User user = (User) session.getAttribute("user");
        itemList = itemService.getAllItemByseller(user.getId());
    }
    int totalPage = (int) Math.ceil(((double)itemList.size() / 10));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap Core CSS -->
	<jsp:include page="headerjsp_1.jsp" />
	
<style type='text/css'>
.btn {
    
    font-size: 20px;
    
      
    color:white
}
</style>
<title>The Book Store - My Store</title>
</head>
<body>
	<!-- contrainer -->
<div class="container">
	<!-- contrainer -->
	<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->
		<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->
	<% if (loginBool == "true") {%>	
		<!-- after login -->
		<jsp:include page="customerheader1.jsp" />		
	<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader1.jsp" />
	<% } %>
	
	

    <% if (loginBool == "true") {
        if (itemList.size() != 0){
    %>
    <!-- after login -->
    
       
        

           

                <table class="table table-inverse">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author/Editor</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Press to Pause/UnPause<th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Item temp : itemList){%>
                    <form action="control" method="get">
                    <tr class="itemToShow">
                        <td><%=temp.getTitle()%></td>
                        <td><%=temp.getAuthors()%></td>
                        <td><%=temp.getPrice()%></td>
                        <td><%=temp.getQuantity()%></td>
                        <% if (temp.isPaused()){%>
                        <input type="hidden" name="id" value="<%=temp.getId()%>">
                        <input type="hidden" name="action" value="unPauseItem">
                        <td><button type="submit" class="btn btn-link"><span
								class="glyphicon glyphicon-ok-circle"></span>unPause</button></td>
                        <%} else {%>
                        <input type="hidden" name="id" value="<%=temp.getId()%>">
                        <input type="hidden" name="action" value="pauseItem">
                        <td><button type="submit" class="btn btn-link"><span
								class="glyphicon glyphicon-remove-circle"></span>Pause</button></td>
                        <% } %>
                    </tr>
                    </form>
                    <% } %>
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
        <div class="col-md-4 col-md-offset-4" align="center">
            <a class="btn btn-link" href="addItem.jsp" title="">
                <span class="icon"></span>
                <span class="text"><span class="glyphicon glyphicon-plus"></span>Add New Item</span>
            </a>
        </div>

        <%} else {%>
        
        <div class="container theme-showcase" role="main">
            <div class="row" style="margin: 40px" align="center">
                <div class="col-md-8 col-md-offset-1">
                	<h1>Store is empty :(</h1>
                </div>
            </div>

        <div class="col-md-4 col-md-offset-4" align="center">
			 <a class="btn btn-primary " href="addItem.jsp" title="">
               	<span class="icon"></span>
               	<span class="text">Add New Item</span>
             </a>
		</div>
        </div>
        <!-- container_div starting-->
</div>
<!-- container_div ending-->
        <%}%>
    <%} else {%>
    <!-- before login -->
        <div class="container theme-showcase" role="main">
            <div class="row" style="margin: 40px" align="center">
            	<div class="col-md-8 col-md-offset-1">
                	<h1><span class="glyphicon glyphicon-exclamation-sign"></span> Please Log in!</h1>
                </div>
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