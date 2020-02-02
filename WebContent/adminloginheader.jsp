<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   	<!-- title -->
	<font color="white"><h1 >Administration </h1></font>
	
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
                    <a class="navbar-brand" href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home</a>
                    <a  class="navbar-brand"  href="#" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-log-in"></span> Sign in</a>
                </div>
            

            </div> 
        </nav>
        
        
        
        
        <!-- Modal -->
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Sign in</h4>
                    </div>

                    <form action="control" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="action" value="login">
                            <div class="form-group">
                                <label for="InputUsername">Username</label>
                                <input type="text" class="form-control" id="InputUsername" name="username" placeholder="Username">
                            </div>
                            
                            <div class="form-group">
                                <label for="InputPassword">Password</label>
                                <input type="password" class="form-control" id="InputPassword" name="password" placeholder="Password">
                            </div>
  
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </form>
                    
                    <p style="text-align:right; margin-right:20px;">Don't have an account? <a href="registration.jsp"> Sign up now </a> </p>


                </div>
            </div>
        </div>
        
        
</body>


</html>