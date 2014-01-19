<jsp:include page="include_header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>

<script type="text/javascript">
	var contexPath = "<%=request.getContextPath()%>";
</script>
</head>
<div id="menu">
	<ul>
			<li><a  href="/onlinetutoring" title="home">HOME</a></li>
			<sec:authorize url="/questionPost*">
				<li><a class="current" href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
		 	</sec:authorize>	
		 	<li><a href="/onlinetutoring/advancedSearchQuestion" title="advanced search">ADVANCED SEARCH</a></li>
		 	<li><a href="/onlinetutoring/viewProfile" title="view profile">VIEW PROFILE</a></li>
		 	<sec:authorize url="/admin*">
		 		<li><a href="/onlinetutoring/admin/userMaintenance" title="admin">ADMIN</a></li>
		 	</sec:authorize>
		<!--	<li><a href="#" title="Articles">ARTICLES</a></li>
			<li><a href="#" title="Abous us">ABOUTS US</a></li>
			<li><a href="#" title="Contact">CONTACT</a></li>
			-->
	</ul>
</div>

<div id="main">
	<div id="right_side"></div>
	<div id="left_side">
		<div class="intro">
			 <div class="pad">This is question post page. </div> 

		</div>
		<div class="mpart">
			<h3>ASK Question</h3>
			<br>
			<form:form method="POST" modelAttribute="question">
				<table>
					<tr>
						<td>Question Category</td>
						<td><form:select path="category_id">
								<form:options items="${categories}" />
							</form:select></td>
					</tr>
					<tr>
						<td>Title</td>
						<td><form:input path="question_title" /></td>
					</tr>
					<tr>
						<td>Question Body</td>
						<td><form:textarea path="question_description" /></td>
					</tr>
					<tr>
						<td>Tags</td>
						<td><form:input path="tags" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Post" /></td>
					</tr>
				</table>
			</form:form>

 		<div id="searchResult"></div>

			<div id="info" class="success"></div>
			<!-- 	<div class="greybox">
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi.  Duis semper. 
		</div>
		 -->
		</div>

	</div>
	<jsp:include page="include_footer.jsp" />
</div>

</body>
</html>


