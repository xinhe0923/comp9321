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
	<style type='text/css'>
.btn {
    
    font-size: 20px;
    
      
    color:white
}
</style>
<title>The Book Store - Add New Item To Store</title>
</head>
<body>


		

        
        
        
        
        
        <form action="control" method="post" class="form-horizontal">
        <input type="hidden" name="action" value="addItem">
        	<div class="container">
        			<!-- CHOOSE JSP DEPENDS ON AUTHORITY -->

		<% if (loginBool == "true") {%>	
		<!-- after login -->
		<jsp:include page="customerheader2.jsp" />		
		<%} else {%>
		<!-- before login -->
		<jsp:include page="homeloginheader2.jsp" />
		<% } %>
        	
        	
        		<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Title</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="title" placeholder="Must input...">
    				</div>
    			</div>
    			
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Author/Editor</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="author" placeholder="Must input...">
    				</div>
    			</div>

    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Publication Date</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="publicationdate" placeholder="Input...">
    				</div>
    			</div>
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Venues</label>
    				<div class="col-md-6">
      					<select name="venues" id="venues" class="form-control">
						<option value="bpm">BPM</option>
						<option value="vldb">VLDB</option>
						<option value="www">WWW</option>			
						</select>
    				</div>
    			</div>
    			
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Price</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="price" placeholder="Input...">
    				</div>
    			</div>
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Quantity</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="quantity" placeholder="Input...">
    				</div>
    			</div>
    			
    			<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Image(URL)</label>
    				<div class="col-md-6">
      					<input class="form-control" type="text" id="#" name="image" placeholder="Input...">
    				</div>
    			</div>
    			

			
			<!------- type search bar --------->
			
			
				<div class="form-group form-group-md">
    				<label class="col-md-3 control-label">Publication Type</label>
    				<div class="col-md-6">
      					<select name="typesearch" id="searchaction" class="form-control">
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
						<button type="submit" class="btn btn-link"><span
								class="glyphicon glyphicon-plus"></span>Add To Shop</button>
					</div>
				</div>
			
        
    		</div>
    	
    	</form>

    

   




>





    <!-- Bootstrap Core JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript" src="CSS/bootstrap.js"></script>
    



</body>
</html>