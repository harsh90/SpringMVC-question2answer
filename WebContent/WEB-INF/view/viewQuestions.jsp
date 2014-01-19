
<jsp:include page="include_header.jsp" />


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<script type="text/javascript">
	var data = eval('(' + '${questionDataJson}' + ')');
	$(document).ready(function() {
		 
	 	generateAnswerList(data);	

	});
	function qa_vote_click(element){
		  
		var elm=element.name.split("_");
		var id = elm[1];
		var type = elm[3];
		var voteDivID;
		if(type=="a"){
			voteDivID="av"+id;
		}else{
			voteDivID="qv"+id;
		}
		var json = {
			"elementName" : element.name,
			"voteStatus" : element.value
		};

		$.ajax({
			url : "/onlinetutoring/vote",
			data : JSON.stringify(json),
			type : "POST",

			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				 if(response.status!="ERROR"){
				 	$("#"+voteDivID).text(response.result);
				 }else{
					if(response.status!="ERROR") {
							alert(response.status);
					}
				 }
			}
		});
		
		
		
	}
	function generateAnswerList(data) {
		 var answerView = "";
		///generate question div
		for ( var index in data.result.answers) {
 		 
		 answerView +="<div id='a"+data.result.answers[index].answerId+"'><div class='qa-q-item-stats'><div class='qa-voting qa-voting-net' id='voting_137304'>"+
			 "<div class='qa-vote-buttons qa-vote-buttons-net'> <font><font class=''><input title='Click here to vote up'"+
			 "name='vote_"+data.result.answers[index].answerId+"_1_a_"+data.result.answers[index].answerId+"' onclick='return qa_vote_click(this);' type='submit' value='+ '"+
			 "class='qa-vote-first-button qa-vote-up-button'></font></font> <font><font class=''><input title='Please click to vote down'"+
			 "name='vote_"+data.result.answers[index].answerId+"_-1_a_"+data.result.answers[index].answerId+"' onclick='return qa_vote_click(this);' type='submit' value='-'"+
			 "class='qa-vote-second-button qa-vote-down-button'></font></font> </div> <div class='qa-vote-count qa-vote-count-net'>"+
			 "<span class='qa-netvote-count'> <span class='qa-netvote-count-data' id='av"+data.result.answers[index].answerId+"'><font><font>" + data.result.answers[index].score + "</font></font></span>"+
			 "<span class='qa-netvote-count-pad'><font><font>vote</font></font></span> </span> </div> <div class='qa-vote-clear'></div>"+
			 "</div> </div> <p>" + data.result.answers[index].answerDescription + "</p> <div class='date'> Answerd By <a title=' Click to view profile ' href='/onlinetutoring/viewProfile/"+data.result.answers[index].authorId+"'>"+ data.result.answers[index].authrName+ "</a>"+ 
			 "</div><br>" + data.result.answers[index].timeCreated + "</div>";
		}
		//generate ansswers

		//alert(answerView);

		//answerView += "</div>";
		$('#answersDiv').hide('slow');
		$('#answersDiv').html("");
		$('#answersDiv').html(answerView);
		$('#answersDiv').show('slow');
	}

	function setbg(color) {
		document.getElementById("styledAnswer").style.background = color;
	}
	function doAjaxPostAnswer() {
		var answer = $('#styledAnswer').val();
		//alert(answer);
		if (answer == "" || answer == "Enter your answer here...") {
			alert("Answer cannot be empty");
			return;
		}

		var questionId = data.result.question_id;
		var json = {
			"questionId" : questionId,
			"answer" : answer
		};

		$.ajax({
			url : "/onlinetutoring/postAnswer",
			data : JSON.stringify(json),
			type : "POST",

			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(response) {
				//alert(data);
				if (response.status=="SUCCESS") {
					//alert("camee to succes");
					generateAnswerList(response);
					
					alert(response.result.noOfAnswers);
					$('#answrConunt').text(response.result.noOfAnswers);
					$('#styledAnswer').val("Enter your answer here...");
					
				}else{
					alert(response.status);
				}

			}
		});
	}
 
</script>
</head>
<div id="menu">
	<ul>
			<li><a class="current" href="/onlinetutoring" title="home">HOME</a></li>
			<sec:authorize url="/questionPost*">
				<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
		 	</sec:authorize>	
		 	<li><a href="/onlinetutoring/advancedSearchQuestion" title="advanced search">ADVANCED SEARCH</a></li>
		 	<li><a href="/onlinetutoring/viewProfile" title="view profile">VIEW PROFILE</a></li>
		 	<sec:authorize url="/admin*">
		 	<li><a href="/onlinetutoring/admin/userMaintenance" title="admin">ADMIN</a></li>
			</sec:authorize>
	</ul>
</div>

<div id="main">
	<div id="right_side"></div>
	<div id="left_side">

		<div class="intro">
			 <div class="pad">This is question view page. </div>  
		</div>
		<div class="mpart">
			<div id="questionView">
				<div class="qa-q-item-stats">
					<div class="qa-voting qa-voting-net" id="voting_137304">
						<div class="qa-vote-buttons qa-vote-buttons-net">
							<font><font class=""><input
									title="Click here to vote up" name="vote_${questionDataJson.result.question_id}_1_q_${questionDataJson.result.question_id}"
									onclick="return qa_vote_click(this);" type="submit" value="+"
									class="qa-vote-first-button qa-vote-up-button"></font></font> <font><font
								class=""><input title="Please click to vote down"
									name="vote_${questionDataJson.result.question_id}_-1_q_${questionDataJson.result.question_id}"
									onclick="return qa_vote_click(this);" type="submit" value="-"
									class="qa-vote-second-button qa-vote-down-button"></font></font>
						</div>
						<div class="qa-vote-count qa-vote-count-net">
							<span class="qa-netvote-count"> <span
								class="qa-netvote-count-data"  id="qv${questionDataJson.result.question_id}"><font><font>${questionDataJson.result.score}
									</font></font></span><span class="qa-netvote-count-pad"  ><font><font>votes </font></font></span>
							</span>
						</div>
						<div class="qa-vote-clear"></div>
					</div>
					<span class="qa-a-count"> <span id="answrConunt" class="qa-a-count-data"><font><font>${questionDataJson.result.noOfAnswers}
							</font></font></span><span class="qa-a-count-pad"><font><font>answers
							</font></font></span>
					</span> <span class="qa-view-count"> <span
						class="qa-view-count-data"><font><font>${questionDataJson.result.views}</font></font></span><span
						class="qa-view-count-pad"><font><font>Views</font></font></span>
					</span>
				</div>
			<h3>${questionDataJson.result.question_title} </h3>

			<p>
				${questionDataJson.result.question_description} <a title="" href="#"></a><strong></strong>
			</p>
			<blockquote> 
			
				<p> 
				<c:choose>
				  <c:when test="${hasPriviledgeToClose && isClosed==false}"> 
				   <a title="click to close question" href="/onlinetutoring/closeQuestion/${questionDataJson.result.question_id}"> CLOSE QUESTION</a>
				  </c:when>
 			 	</c:choose> 
 			 	 <c:choose>
				  <c:when test="${hasPriviledgeEditDelete && isClosed==false}"> 
				    &nbsp;&nbsp;<a title="click to edit" href="/onlinetutoring/updateQuestion?questionId=${questionDataJson.result.question_id}">Edit</a> &nbsp;&nbsp;<a title="click to edit" href="/onlinetutoring/questionDelete?questionId=${questionDataJson.result.question_id}"  > Delete</a>
				  </c:when>
 			 	</c:choose>
 			 </p>
			</blockquote>
			<div class="date">
				 <a title="click to view user profile " href="/onlinetutoring/viewProfile/${questionDataJson.result.author_id}">${questionDataJson.result.author_name}</a> . ${questionDataJson.result.time_created}
			</div>
			</div><!-- end of question view div -->
			<h3>Answers</h3>
			<h2></h2>
			<div id="answersDiv">
			<!-- 	<div>
					<div class="qa-q-item-stats">
						<div class="qa-voting qa-voting-net" id="voting_137304">
							<div class="qa-vote-buttons qa-vote-buttons-net">
								<font><font class=""><input
										title="Click here to vote Balmujb"
										name="vote_137304_1_q137304"
										onclick="return qa_vote_click(this);" type="submit" value="+ "
										class="qa-vote-first-button qa-vote-up-button"></font></font> <font><font
									class=""><input title="Please click to vote negative"
										name="vote_137304_-1_q137304"
										onclick="return qa_vote_click(this);" type="submit" value="-"
										class="qa-vote-second-button qa-vote-down-button"></font></font>
							</div>
							<div class="qa-vote-count qa-vote-count-net">
								<span class="qa-netvote-count"> <span
									class="qa-netvote-count-data"><font><font>+1
										</font></font></span><span class="qa-netvote-count-pad"><font><font>vote</font></font></span>
								</span>
							</div>
							<div class="qa-vote-clear"></div>
						</div>

					</div>
					<p>This is answer 1 description</p>
					<div class="date">
						Test 1<a title="#" href="#">Ansswer By Name (11)</a> . 07 Jan 2007
						. 02:56
					</div>
					<br>
				</div>
				   -->
			</div>

		</div>

		<!--END of answersDiv   -->

		<br> <br>
		<div></div>
		
		<sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_MODERATOR">
			<h2>You don not have permissions to answer</h2>
		</sec:authorize>
		<sec:authorize url="/postAnswer*">
		  	<c:choose>
			 	<c:when test="${isClosed==false}">
					 <div id="answerQuestion">
						<h2>Answer question</h2>
					</div>
			 		<textarea name="styledAnswer-textarea" id="styledAnswer"
							onfocus="this.value=''; setbg('#e5fff3');" onblur="setbg('white')">Enter your answer here...</textarea>
					 <input type="button" value="Post yout answer" onclick="doAjaxPostAnswer()">
			 	 </c:when>
			</c:choose>
		</sec:authorize>			
		<a href="/onlinetutoring" title="click to back"> Back </a>
		 <div id="info" class="success"></div>
	</div>

	<!-- <h3>Search Resuls</h3> -->
	<!-- <h2>Expect Great Things</h2>-->

</div>




<jsp:include page="include_footer.jsp" />

</body>
</html>


