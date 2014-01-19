<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include_header.jsp" />  
<div id="menu">
		<ul>	
			<li><a href="/onlinetutoring/searchquestions" title="home">HOME</a></li>
			<li><a  class="current" href="/onlinetutoring/searchquestions" title="advanced search">ADVANCED SEARCH</a></li>
			<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
			<li><a href="#" title="view profile">MY PROFILE</a></li>
		<!--	<li><a href="#" title="Articles">ARTICLES</a></li>
			<li><a href="#" title="Abous us">ABOUTS US</a></li>
			<li><a href="#" title="Contact">CONTACT</a></li>
			-->
		</ul>
	</div>

	<h3>Message : ${message}</h3>	
	<h3>Username : ${username}</h3>	
	
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
	
	<c:forEach items="${resSearchResults}" var="question">
		<table>
			<tr>
				<td>Question Title</td>
				<td>${question.questionTitle}</td>
			</tr>
			<tr>
				<td>Question Description</td>
				<td>${question.questionDescription}</td>
			</tr>
			<tr>
				<td>Question Author</td>
				<td>${question.authorId}</td>
			</tr>
			<tr>
				<td>Date</td>
				<td>${question.timeCreated}</td>
			</tr>
			<tr>
				<td>Tags</td>
				 
			</tr>
			
			
			<tr>
				<td colspan="2"><a href="questionDelete?questionId=${question.questionId}">Delete</a>
				</td>
				<td colspan="2"><a href="updateQuestion?questionId=${question.questionId}">Edit</a>
				</td>
			</tr>
		</table>
		<hr />
	</c:forEach>
	<a href="/onlinetutoring/questionPost">Post Question</a>
</body>
</html>