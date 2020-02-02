<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
   	<!-- title -->
	<font color="white"><h1 class="text-center">Bookstore</h1></font>

	
	<!-- title -->

	<nav class="navbar navbar-inverse ">
	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<splan class="icon-bar"></splan>
				<splan class="icon-bar"></splan>
				<splan class="icon-bar"></splan>
			</button>
			<a class="navbar-brand" href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home</a>
		    <a class="navbar-brand" href="shoppingcart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</a> 
		    <a class="navbar-brand" href="store.jsp"><span class="glyphicon glyphicon-book"></span>My Store</a>
			<a class="navbar-brand" href="wishlist.jsp"><span class="glyphicon glyphicon-book"></span>My Wishlist</a>
		</div>

		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
            <li>
                        	<form class="navbar-form navbar-left" method="get" action="control">
				<div class="form-group">
				     <!-- submit the hidden information to the servelet -->
				    <input type="hidden" name="action" value="basicsearch"/>
				    <!-- search_bar -->
					<input type="text" class="form-control" placeholder="Search"name="keyword">
									<!-- search_buttom -->
				<button type="submit" class="btn btn-link"><span
					class="glyphicon glyphicon-search"></span></button>
				</div>

			</form>
            
            
            </li>



				<!-- 导航栏里面的li元素 下拉菜单 start-->
				<!-- 下拉菜单的图像 ending-->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <!--下拉图标的设置 --> <span
						class="glyphicon glyphicon-align-justify"></span></a> <!-- 下拉菜单的图像 ending-->

					<ul class="dropdown-menu">
						<li><a href="control?action=logout"><span
								class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
						<li><a href="profile.jsp"><span
								class="glyphicon glyphicon-user"></span> My Profile</a></li></li>
                        <li><a href="advsearch.jsp"><span
					class="glyphicon glyphicon-search"></span> Advsearch</a>
					</ul> <!-- 导航栏里面的li元素 下拉菜单 ending-->
			</ul>
		</div>
	
	</nav>
</body>


</html>