<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body >
      
	<!-- title -->
	<font color="white"><h1 class="text-center">Bookstore</h1></font>

	
	<!-- title -->
	


	<!-- nav_bar -->
	<nav class="navbar navbar-inverse"> 
	
	
	<!-- 导航栏左边元素starting-->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar-menu"
			aria-expanded="false">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="home.jsp"><span
			class="glyphicon glyphicon-home"></span> Home</a>
	</div>
	<!-- 导航栏左边元素 ending --> 
	
	
	
	

	
	
	
	<!-- 导航栏的右边 starting -->
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
		



			<!-- 导航栏里面的li元素 下拉菜单 start-->
			<!-- 下拉菜单的图像 ending-->
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"> <!--下拉图标的设置 --> <span
					class="glyphicon glyphicon-align-justify"></span></a> 
					<!-- 下拉菜单的图像 ending-->

				<ul class="dropdown-menu">
					<li><a href="registration.jsp"> Sign up</a></li>
					<li><a href="#" data-toggle="modal" data-target="#myModal"> Sign in</a></li>
					<li><a href="advsearch.jsp"> Advance search</a>
				</ul></li>

			<!-- 导航栏里面的li元素 下拉菜单 ending-->
		</ul>
	</div>

	</nav>
	<!-- 导航栏的右边ending -->
        
      
        
        
        
                <!--  boostrap 下拉菜單--> 
        <div id="myModal" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        
                        <font color="white"><h4 class="modal-title">Sign in</h4></font>
                    </div>

                    <form method="post" action="control">
                        <div class="modal-body">
                            
                            <div class="form-group">
                                <input type="hidden" name="action" value="login">
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
                    
                    <p style="text-align:right; margin-right:20px;" >Don't have an account? <a href="registration.jsp">	sign up now  </a> </p>
                    


                </div>
            </div>
        </div>
        
        <!--  boostrap 下拉菜單-->  
        
        
</body>


</html>