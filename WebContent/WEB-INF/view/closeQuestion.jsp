<jsp:include page="include_header.jsp" />

<head>

<script type="text/javascript">
	var contexPath = "<%=request.getContextPath()%>";
</script>
</head>
<div id="menu">
	 
</div>

<div id="main">
	<div id="right_side"></div>
	<div id="left_side">
		<div class="intro"></div>
		<div class="mpart">
			<h2>${message}</h2>
			<h3>Change password Question</h3>
		<div class="formLayout" style="width:800px; margin:0 auto;">
			<form name="closeQuestion" method="post" action="/onlinetutoring/closeQuestion" OnSubmit="return fncSubmit();">
 				<table>

					<tr>
						<td>Question Title</td>
						<TD>${questionTitle}</td>
					</tr>
					<tr>
						<td>Reason for closing the question : </td>
						<td><input name="reason" type="text" id="reason" size="45" style="width: 300px;"></td>
					</tr>
					 <tr>
						<td> </td>
						<td><input name="questionId" type="hidden" id="questionId" value="${questionId}"></td>
					</tr>	
					<tr>
						<td> <a href="/onlinetutoring/advancedSearchQuestion" title="click to back"> Back </a></td>
						<td><input type="submit" name="Submit" value="save"></td>
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
if(document.ChangePasswordForm.reason.value == "")
{
	alert('Please Reason for closing the question');
	document.ChangePasswordForm.value.focus();
	return false;
} 
 
document.ChangePasswordForm.submit();
}
</script>
</body>
</html>


