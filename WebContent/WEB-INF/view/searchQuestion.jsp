<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<h1>Search Question ........</h1>
	<table>
		<tr><td colspan="2">
			<div id="error" class="error"></div></td>
		</tr>
		<tr>
			<td>Question Category </td>
			<td> <form:select path="category" id="category" items="${categoryList}" /><br/></td>
		</tr>
		<tr>
			<td> Question Title: </td>
			<td> <input type="text" id="title"><br/></td>
		</tr>
		<tr>
			<td> Question Description: </td>
			<td> <input type="text" id="question"><br/></td>
		</tr>
		<tr>
			<td> Tags: </td>
			<td> <input type="text" id="tags"><br/></td>
		</tr>
		<tr>
			<td> Sort: </td>
			<td> <select id="sort" name="sort">
				   <option value="new">New</option>
				   <option value="unanswerd">UN Answered</option>
				    <option value="old">Old</option>
					</select>	
				<br/></td>
		</tr>
		<tr><td colspan="2">
			<input type="button" value="Add Users" onclick="doAjaxPost()"><br/></td>
		</tr>
		<tr><td colspan="2"><div id="info" class="success"></div></td></tr>
	</table>
	
	<div id="footer">
		<div class="right">&copy; Copyright 2013, Harshitha de Silva - Design <br />
		<!--	<a href="/rss/" title="RSS Feed">RSS Feed</a> - <a href="http://validator.w3.org/check?uri=referer" title="Validate">XHTML
		</a> - <a href="http://jigsaw.w3.org/css-validator/check/referer" title="Validate">CSS</a>-->
		</div>
	</div>
</div>
	
</body>
</html>
  --%>