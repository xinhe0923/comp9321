<%@ page import="dto.Item" %>
<%@ page import="service.ItemService" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
	String loginBool = (String) session.getAttribute("login");
    ItemService itemService = new ItemService();
    List<Item> elements = itemService.getRandom10();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>

<jsp:include page="headerjsp_1.jsp" />
</head>
<body >
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
     
     
    
    
    
    
     <!--show the random ten books  start-->
        <div >
        <%if (elements.size()>0){%>

                <table class="table table-inverse">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author/Editor</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Item temp : elements ){%>
                    <tr>
                        <td><a href="control?action=showDetail&id=<%=temp.getId()%>"><%=temp.getTitle()%></a></td>
                        <td><%=temp.getAuthors()%></td>
                        <td><%=temp.getPrice()%></td>
                        <td><%=temp.getQuantity()%></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
  
         <!--show the random ten books  ending->


            <!-- Picture Can remove -->

     

            <!-- Picture End -->
            <%}else {%>
            <div class="row" style="margin: 40px" align="center">
                <div class="col-md-8 col-md-offset-1">
                    <h1>Sorry, no matching data found!</h1>
                </div>
            </div>
            <%}%> 
     
     
     
<!-- container_div starting-->
</div>
<!-- container_div ending-->


</body>
</html>