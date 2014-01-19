<jsp:include page="include_header.jsp" />

<head>

<script type="text/javascript">
	var contexPath = "<%=request.getContextPath()%>";
</script>
</head>
<div id="menu">
	<!-- <ul>	
			<li><a href="/onlinetutoring/searchquestions" title="home">HOME</a></li>
			<li><a href="/onlinetutoring/searchquestions" title="advanced search">VIEW ALL </a></li>
			<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
			<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
			<li><a class="current" href="/onlinetutoring/questionPost" title="advanced search">ADVANCED SEARCH</a></li>
			<li><a href="#" title="Articles">ARTICLES</a></li>
			<li><a href="#" title="Abous us">ABOUTS US</a></li>
			<li><a href="#" title="Contact">CONTACT</a></li>
			
		</ul> -->
</div>

<div id="main">
	<div id="right_side"></div>
	<div id="left_side">
		<div class="intro"></div>
		<div class="mpart">
			<h1>${message}</h1>
			<h3>Change password</h3>
			<div class="formLayout" style="width:800px; margin:0 auto;">
			<form name="ChangePasswordForm" method="post"
				action="/onlinetutoring/processChangePassword" OnSubmit="return fncSubmit();">

				<table>

					<tr>
						<td>OLD PASSWORD</td>
						<TD><input name="OldPassword" type="password" id="OLDpwd"
							size="20"></td>
					</tr>
					<tr>
						<td>New Password</td>
						<td><input name="newpassword" type="password"
							id="newpassword"></td>
					</tr>
					<tr>
						<td>Confirm Password</td>
						<td><input name="conpassword" type="password"
							id="conpassword"></td>
					</tr>
					<tr>
						<td> <a href="/onlinetutoring/viewProfile" title="change password"> Cancel </a></td>
						<td><input type="submit" name="Submit" value="Save"></td>
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


