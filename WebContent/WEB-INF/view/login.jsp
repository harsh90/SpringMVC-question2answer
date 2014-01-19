<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

	<!-- General meta information -->
	<title>Login Form </title>
	 
	 
	<!-- // General meta information -->
	
	
	<!-- Load Javascript -->
	 
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.query-2.1.7.js" />"></script>
	<script src="<c:url value="/resources/js/rainbows.js" />"></script>
	<!-- // Load Javascipt -->

	<!-- Load stylesheets -->
	
	<link href="<c:url value="/resources/css/logincss.css"/>" rel="stylesheet"> 
	
	<!-- // Load stylesheets -->
	
<script>


	$(document).ready(function(){
 
	$("#submit1").hover(
	function() {
	$(this).animate({"opacity": "0"}, "slow");
	},
	function() {
	$(this).animate({"opacity": "1"}, "slow");
	});
 	});


</script>
	
</head>
<body>

	
	<div id="wrapper">
		<div id="wrappertop">
		
	 
		
		</div>

		<div id="wrappermiddle">

			<h2>Login</h2>
			<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
			<div id="username_input">

				<div id="username_inputleft"></div>

				<div id="username_inputmiddle">
				
					<input type="text" name="j_username" id="url" value="User Name" onclick="this.value = ''">
					<img id="url_user" src="<c:url value="/resources/images/mailicon.png" />"  alt="">
				
				</div>
 
				<div id="username_inputright"></div>

			</div>

			<div id="password_input">

				<div id="password_inputleft"></div>

				<div id="password_inputmiddle">
				 
					<input type="password" name="j_password" id="url" value="Password" onclick="this.value = ''">
					<img id="url_password" src="<c:url value="/resources/images/passicon.png" />"  alt="">
				 
				</div>

				<div id="password_inputright"></div>
				
			</div>
		 
			
				
			<div id="submit">
				Remember me <input type="checkbox" name="_spring_security_remember_me"  />
				 <input id="submitbutton"  type="submit" value="Sign In" align="center" />
			</div>

 			</form>
			<div id="links_left">
			<a href="/onlinetutoring/home">Cancel</a>
			<br>
			<a href="#">Forgot your Password?</a>

			</div>

			<div id="links_right"><a href="/onlinetutoring/userRegistration">Not a Member Yet?</a></div>
			
		</div>

		<div id="wrapperbottom"></div>
		
		 
	</div>
	
	<c:if test="${not empty error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.  Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
	</c:if>

</body>
</html>