<jsp:include page="include_header.jsp" /> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
 
 <head>

<script type="text/javascript">
	var contexPath = "<%=request.getContextPath() %>";
</script>
</head>
	<div id="menu">
		<ul>	
			<li><a href="/onlinetutoring" title="home">HOME</a></li>
			<sec:authorize url="/questionPost*">
				<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
		 	</sec:authorize>	
		 	<li><a class="current" href="/onlinetutoring/advancedSearchQuestion" title="advanced search">ADVANCED SEARCH</a></li>
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
		<div id="right_side">
			<!--<div class="pad">
				<img src="images/pic.jpg" alt="" />
				<h3>Categories</h3>
				<ul>	
							<li><a href="#" title="Articles">Templates (15)</a></li>
							<li><a href="#" title="Gallery">Internet (10) </a></li>
							<li><a href="#" title="Affiliates">Tutorials (23)</a></li>
							<li><a href="#" title="Articles">Photoshop (11)</a></li>
							<li><a href="#" title="Abous us">sNews (16)</a></li>
							<li><a href="#" title="Contact">Personal (5)</a></li>
						
				</ul>
			
				<h3>Directory</h3>
				<p><a href="http://www.links4se.com" title="SEO friendly directory">Links4se.com</a> is a seo-friendly and human-edited general directory. Featured links need to be paid only $4.95 for unlimited time and regular links only $1.95!</p>
				<h3>Creative News</h3>
				<p><a href="http://www.creativenews.cn" title="Creative News">Creative News</a> is a place for web developers searching for fresh news on web development with one goal - to put their creativity into action.</p>
				<h3>Css Heaven</h3>
				<p><a href="http://www.cssheaven.com" title=" CSS Gallery">CSS Heaven</a> has one objective to provide you with a central resource to view a wide range of eye catching and innovative web design plus inspire you to create new and exciting web designs. </p>
				
			</div>
			-->
		</div>
		<div id="left_side">
			<div class="intro">
			  <div class="pad">This is advanced search page. </div> 
			 
			</div>
			<div class="mpart">
			<h3>Search Question</h3>
			<br>
			<!-- <h2>pr</h2> -->
			<!-- <div class="greybox"> -->
			<table>
		<tr><td colspan="2">
			<div id="error" class="error"></div></td>
		</tr>
		<tr>
			<td>Question Category : </td>
			<td> <select id="categoryies"    >
			  	 	<option value="ALL" selected>-ALL-</option>
				   <option value="1">Computer Programming</option>
				   <option value="2">Music</option>
				  
					</select>	<%-- <form:select path="categoryies" items="${categoriesList}" /> --%><br/></td>
		</tr>
		<tr>
			<td> Question Title : </td>
			<td> <input type="text" id="title" size="73"><br/></td>
			
		</tr>
		<tr>
			<td> Question Description : </td>
			<td> <input type="text" id="question"  size="73"><br/></td>
		</tr>
		<tr>
			<td> Tags to search : </td>
			<td> <input type="text" id="tags" size="73" title="Enter tags seperated by comma" ><br/></td>
		</tr>
		<tr>
			<td> Sort : </td>
			<td> <select id="sort" id="sort"  >
				   <option value="NEW">New</option>
				   <option value="OLD">Old</option>
				  
					</select>	
				<br/></td>
		</tr>
		<tr><td colspan="2">
			 </td>
		</tr>
		<tr><td> </td></tr>
	</table>
 
			<div class="date"><input type="button" value="Search" onclick="doAjaxGetSearch()"></div>
		 
		 	<div id="searchResult"></div>
				   
		 	<div id="info" class="success">
			</div> 
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