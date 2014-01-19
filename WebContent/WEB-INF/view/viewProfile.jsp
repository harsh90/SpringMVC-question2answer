<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Online Tutoring</title>
	 
	<meta http-equiv="Content-Language" content="English" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	 
 	<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
 	<link href="<c:url value="/resources/css/userprofile.css"/>" rel="stylesheet">
 	<%-- <link href="<c:url value="/resources/css/stackcss.css"/>" rel="stylesheet"> --%> 
 	<link rel="shortcut icon" href="<c:url value="/resources/css/images/favicon.icov"/>" > 
<!-- Add jQuery to your website if you don't have it already --> 
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/advancedsearch.js" />"></script>
<script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.livequery.js" />"></script>
<script src="<c:url value="/resources/js/jquery.timeago.js" />"></script>
<script src="<c:url value="/resources/js/jquery.pages.js" />"></script>


<!-- JAVASCRIPT to clear search text when the field is clicked -->
<script type="text/javascript">
$(function() {
	$("#tfq2b").click(function() {
		if ($("#tfq2b").val() == "Search for Question"){
			$("#tfq2b").val(""); 
		}
	});
});
 
function validateSearch(){
	if($("#tfq2b").val() == "Search for Question"){
		return false;
	}
	if($("#tfq2b").val() == ""){
		return false;
	}
	return true;
}
</script>
 
</head>
<body >
 
<div class="content">
	<div id="top">
				<div id="icons">
				<form name="searchForm" method="get" action="/onlinetutoring/searchHeaderQuestions" OnSubmit="return validateSearch();">
					<input type="text" id="tfq2b" class="tftextinput2" name="tfq2b" size="40" maxlength="120" value="Search for Question">
					<input type="submit" value=">" class="tfbutton2">
				</form>	
				<!--
					<a href="/index.html" title="Home page"><img src="images/home.gif" alt="Home" /></a>
					<a href="/contact/" title="Contact us"><img src="images/contact.gif" alt="Contact" /></a>
					<a href="/sitemap/" title="Sitemap"><img src="images/sitemap.gif" alt="Sitemap" /></a>
				-->	
					<sec:authorize access="isAuthenticated()">
					    logged in as <sec:authentication property="principal.username" /> 
					    <a href="<c:url value="j_spring_security_logout" />" > Logout <sec:authentication property="principal.username" /> </a>
					</sec:authorize>
					<sec:authorize access="! isAuthenticated()">
					    not logged in 
					    <a href="<c:url value="/login" />"> Login </a>&nbsp;&nbsp;
						<a href="/onlinetutoring/userRegistration"> Create an Account </a>&nbsp;&nbsp;
					</sec:authorize>
				</div>
				<h1>Online Tutoring</h1>
				<h5></h5>
				<!--<h2></h2>-->
	</div>
 
 
	<div id="menu">
		<ul>	
			<li><a  href="/onlinetutoring" title="home">HOME</a></li>
			<sec:authorize url="/questionPost*">
				<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
		 	</sec:authorize>	
		 	<li><a href="/onlinetutoring/advancedSearchQuestion" title="advanced search">ADVANCED SEARCH</a></li>
		 	<li><a class="current" href="/onlinetutoring/viewProfile" title="view profile">VIEW PROFILE</a></li>
		 	<sec:authorize url="/admin*">
		 		<li><a href="/onlinetutoring/admin/userMaintenance" title="admin">ADMIN</a></li>
		 	</sec:authorize>
		</ul>
	</div>

	<div id="main">
		<div id="right_side">
			
		</div>
		<div id="left_side" class="viewProfile">
			<div class="intro">
			  <div class="pad">This is view profile page. </div> 
			 
			</div>
		 	<div class="mpart">
	<%-- 		 <h2> ${message}</h2> --%>
			 <div id="w">  
    <div id="content" class="clearfix">
       
      <h1>View Profile</h1>

      <nav id="profiletabs">
        <ul class="clearfix">
          <li><a href="#bio" class="sel">Profile Details</a></li>
          <c:choose>
				  <c:when test="${hasPrevilege}">
				    <li><a href="#settings">Settings</a></li>
				  </c:when>
 		 </c:choose>
         
        </ul>
      </nav>
      
      <section id="bio">
        <p>Email Address     :</p> ${userData.emailAddress}
        
        <p>User Name         :</p> ${userData.userName}
        
        <p>Loyality Points 	 :</p> ${userData.loyality}
        
        <p>Reputation Points :<p>  ${userData.reputation}
        
        <p>Profile Type 	 :<p>  ${userData.roleId.role}
      </section>
       
      <section id="settings" class="hidden">
        <p class="setting">Edit your user settings:</p>
        
        <p ><span> Change password <a href="/onlinetutoring/changePassword" title="change password"> change </a></span>  </p>
          
      </section>
    </div><!-- @end #content -->
  </div><!-- @end #w -->
			 
			 
		</div>
		
	</div>
	<jsp:include page="include_footer.jsp" /> 
</div>
</div>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
$(function(){
  $('#profiletabs ul li a').on('click', function(e){
    e.preventDefault();
    var newcontent = $(this).attr('href');
    
    $('#profiletabs ul li a').removeClass('sel');
    $(this).addClass('sel');
    
    $('#content section').each(function(){
      if(!$(this).hasClass('hidden')) { $(this).addClass('hidden'); }
    });
    
    $(newcontent).removeClass('hidden');
  });
});
</script>
</body>
</html>


 