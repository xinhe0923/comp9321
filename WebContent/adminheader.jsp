<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   	<!-- title -->
	<font color="white"><h1 class="text-center">Administration </h1></font>
	<!-- title -->
	
        <nav class="navbar navbar-inverse "> 
            <div class="container-fluid"> 
                <div class="navbar-header"> 
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> 
                        <span class="sr-only">Toggle navigation</span> 
                        <splan class="icon-bar"></splan> 
                        <splan class="icon-bar"></splan> 
                        <splan class="icon-bar"></splan> 
                    </button> 
                    <a class="navbar-brand" href="adminmain.jsp"><span class="glyphicon glyphicon-home"></span> Admin Home</a> 
                    <a class="navbar-brand" href="adminItemsSearch.jsp" title=""> <span class="glyphicon glyphicon-search"></span>Check items</a>
				    <a class="navbar-brand" href="adminUsers.jsp" title=""><span class="glyphicon glyphicon-user"></span>Management</a>
                </div>
            
                <div id="navbar" class="navbar-collapse collapse"> 
                    <ul class="nav navbar-nav navbar-right"> 
                        <li><a href="control?action=logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    </ul>  
                </div>
            </div> 
        </nav>
</body>


</html>