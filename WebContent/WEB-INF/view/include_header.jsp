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
	  <link href="<c:url value="/resources/css/userprofile.css"/>" rel="stylesheet">
 	
 	<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"> 
 	<%-- <link href="<c:url value="/resources/css/stackcss.css"/>" rel="stylesheet"> --%> 
 	<link rel="shortcut icon" href="<c:url value="/resources/css/images/favicon.icov"/>" > 
<!-- Add jQuery to your website if you don't have it already --> 
 <script src="<c:url value="/resources/js/jquery.js" />"></script>
 <script src="<c:url value="/resources/js/advancedsearch.js" />"></script>
 <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
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
<body>
 
<div class="content">
	<div id="top">
				<div id="icons">
				 <form name="searchForm" method="get" action="/onlinetutoring/searchHeaderQuestions" OnSubmit="return fncSubmit();">
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