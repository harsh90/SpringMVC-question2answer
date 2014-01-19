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
	 
	</div>
 
 </div>
<div id="main">
	<div id="right_side"></div>
	<div id="left_side">
		<div class="intro"></div>
		<div class="mpart">
			<h2>${message}</h2>
			<h3>Find You Account</h3>
	<div class="formLayout" style="width:800px; margin:0 auto;">
			<form name="forgetPassword" method="post"
				action="/onlinetutoring/processForgetPassword" OnSubmit="return fncSubmit();">

				<table>

					<tr>
						<td>Your email Address : </td>
						<TD><input name="emailAddress" type="text" id="emailAddress"
							size="20"></td>
					</tr>
					 
					<tr>
						<td> <a href="/onlinetutoring" title="Cancel"> Cancel </a></td>
						<td><input type="submit" name="Submit" value="Find"></td>
					</tr>

				</table>
			</form>
</div>
			<div id="info" class="success"></div>

		</div>

	</div>
	<jsp:include page="include_footer.jsp" />
</div>
<script language="javascript">
function fncSubmit()
{ 
if(document.ChangePasswordForm.OldPassword.value == "")
{
alert('Please input old password');
document.ChangePasswordForm.OldPassword.focus();
return false;
} 
if(document.ChangePasswordForm.newpassword.value == "")
{
alert('Please input Password');
document.ChangePasswordForm.newpassword.focus(); 
return false;
} 

if(document.ChangePasswordForm.newpassword.value.length<6){
	alert('password must have minimum 6 characters');
	document.ChangePasswordForm.newpassword.focus(); 
	return false;
}
	
if(document.ChangePasswordForm.conpassword.value == "")
{
alert('Please input Confirm Password');
document.ChangePasswordForm.conpassword.focus(); 
return false;
} 

if(document.ChangePasswordForm.newpassword.value != document.ChangePasswordForm.conpassword.value)
{
alert('Confirm Password Not Match');
document.ChangePasswordForm.conpassword.focus(); 
return false;
} 

document.ChangePasswordForm.submit();
}
</script>
</body>
</html>


