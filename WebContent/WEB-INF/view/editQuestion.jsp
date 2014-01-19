<jsp:include page="include_header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<h3>Edit Question</h3>
		<div class="formLayout" style="width:800px; margin:0 auto;">
			
			<form:form method="POST" commandName="question" action="${pageContext.request.contextPath}/updateQuestion/${question.question_id}">
			<table>
			<tbody>
				<tr>
					<td>Title :</td>
					<td><form:input path="question_title" /></td>
				</tr>
				<tr>
					<td>Question Description:</td>
					<td><form:textarea path="question_description" /></td>
				</tr>
				 
				<tr>
					<td>tags :</td>
					<td><form:input path="tags" title="Seperate tags with a comma"/></td>
				</tr>
				
				 
				<tr>
					<td><input type="submit" value="Save" /></td>
					<td><a href="/onlinetutoring" title="cancel">cancel</a></td>
				</tr>
			</tbody>
				</table>
			</form:form>

		</div>
		 <div id="info" class="success"></div>

		</div>

	</div>
	<jsp:include page="include_footer.jsp" />
</div>
 </body>
</html>


