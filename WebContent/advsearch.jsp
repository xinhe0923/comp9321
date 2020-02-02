<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String loginBool = (String) session.getAttribute("login");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- Bootstrap Core CSS -->
	<jsp:include page="headerjsp_1.jsp" />
	
	
<title>The Book Store - Advance Search</title>
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
        
        <font color="white"><h2 class="text-center">Advance Search</h2></font>
        <form action="control" method="GET" class="form-horizontal">
            
        	<div class="container">
        	
        		<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Title</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="title" name="title" placeholder="Input...">
    				</div>
    			</div>
    			
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Author/Editor</label>
    				<!-- 决定输入框的长度 -->
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="author" placeholder="Input...">
    				</div>
    			</div>
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Publication Date</label>
    				<div class="col-md-3">
      					<input class="form-control" type="text" id="#" name="publicationdate1" placeholder="Input...">
    				</div>
    				<div class="col-md-3">
      					<input class="form-control" type="text" id="#" name="publicationdate2" placeholder="Input...">
    				</div>
    			</div>

    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Venues</label>
    				<div class="col-md-6">
      					<select name="venues" id="venues" class="form-control">
							<option value="all">all</option>
						<option value="bpm">BPM</option>
						<option value="vldb">VLDB</option>
						<option value="www">WWW</option>			
						</select>
    				</div>
    			</div>
    			
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Price</label>
    				<div class="col-md-3">
      					<input class="form-control" type="text" id="#" name="price1" placeholder="Input...">
    				</div>
    				<div class="col-md-3">
      					<input class="form-control" type="text" id="#" name="price2" placeholder="Input...">
    				</div>
    			</div>
			
				<!------- type search bar --------->
			
			
				<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Type</label>
    				<div class="col-md-6">
      					<select name="type" id="searchaction" class="form-control">
							<option value="all">all</option>
							<option value="article">journal</option>
							<option value="inproceedings">conference</option>
							<option value="proceedings">editorship</option>
							<option value="book">book</option>
							<option value="incollection">collection</option>
							<option value="phdthesis">phdthesis</option>
							<option value="mastersthesis">masterthesis</option>
							<option value="www">webpage</option>
							</select>
    				</div>
    			</div>
			
			
			
			
				<div class="row" style="padding-top: 40px">
					<div class="col-md-4 col-md-offset-4" align="center">
						<input type="hidden" name="action" value="advsearch"/>
						<button type="submit" class="btn btn-white">Search</button>
					</div>
				</div>
			
        
    		</div>
    	
    	</form>

    </div>

   









</body>
</html>