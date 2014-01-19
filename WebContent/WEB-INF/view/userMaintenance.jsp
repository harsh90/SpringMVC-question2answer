<jsp:include page="include_header.jsp" /> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
 <head>

<script type="text/javascript">
	var contexPath = "<%=request.getContextPath() %>";
	 
	 
	
	///REST PUT METHOD
	var updateUser = function(userId) {
		var selectLitsId="sel"+userId;
		
		var userRoleId = $("#"+selectLitsId).val();
		alert(userRoleId);
		
		var searchUserName = $('#txtUsername').val();
		var searchRoleId = $('#selFRole').val();
		if(searchUserName==""){
			searchUserName="&";
		}	
	    var JSONObject= {
	        "roleId": userRoleId,
	        "userId": userId,
	        "searchUserName": searchUserName,
	        "searchRoleId": searchRoleId
	    };
	  
	     $.ajax({
	        type: 'PUT',
	        url:  contexPath + "/admin/updateUser",
	        contentType: "application/json; charset=utf-8",
	        data: JSON.stringify(JSONObject),
	        dataType: 'json',
	        async: true,
	        success: function(result) {
	            //alert("At " + result.status);
	            $('#info').html(result.status);
		        $('#info').show('slow');
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            alert(jqXHR.status + " " + jqXHR.responseText);
	        }
	    });  
	};
	 
	
	
 
//REST DELETE METHOD 			
var deleteUser = function(userId) {
        $.ajax({
        type: 'DELETE',
        url:  contexPath + "/admin/deleteUser/" + userId,
        dataType: 'json',
        async: true,
        success: function(result) {
             
            $('#info').html(result.status);
            $('#info').show('slow');
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " " + jqXHR.responseText);
        }
    });
};
	

	function searchUsers() {  
		  // get the form values  
		  var userName = $('#txtUsername').val();
		  var roleId = $('#selFRole').val();
		  if(userName==""){
			  userName="&";
		  }	
		 var tableView="";
		  $.ajax({  
		    type: "GET",  
		    url: contexPath + '/admin/searchUsers/username/' + userName + '/roleId/' + roleId,
		    dataType: 'json',
		    success: function(response){
		      // we have the response 
		     
		    	
		      if(response.status == "SUCCESS"){
		    	  
		    	  tableView=" <div id='spnData' class='CSSTableGenerator'> <table > <tr> <td>  User Name </td> <td >  Role </td> <td> Reputation Points </td>  <td> Loyalty Points </td>  <td>  Pending Requests  </td> <td>  Action </td> </tr>";
		    	  for(var index in response.result){
		    		   tableView+=" <tr>"+
		    					"	 <td >"+
		    					"<a href='viewProfile/"+response.result[index].userId+"'>"+response.result[index].userName+"</a>"+
		    					"</td>"+
		    					"<td><select id='sel"+response.result[index].userId+"'>";
		    					for (var i = 0, keys = Object.keys(response.roles), ii = keys.length; i < ii; i++) {
		    						 /*  console.log('key : ' + keys[i] + ' val : ' + a_hashmap[keys[i]]);
		    						} */	
		    					//for(var index in response.roles){
		    							tableView+="<option value='"+keys[i]+"' ";
		    							if(keys[i]==response.result[index].roleId){
		    								tableView+='selected';
		    							}else{
		    								tableView+=' ';
		    							}
		    							tableView+=">"+response.roles[keys[i]]+"</option>";
		    						}
		    						tableView+="</select>"+
		    			 		 "</td>"+
		    						 "<td >";
		    						 if(response.result[index].roleId ==3){
		    							 tableView+="N/A";
		    						 }else{
		    							 tableView+=response.result[index].reputation;
		    						 }
		    						 tableView +="<td>"+response.result[index].loyality+"</td> <td>";
		    						 console.log(response.result[index].is_pending);
		    						 if(response.result[index].is_pending==1){
		    							 
		    							 
		    							 tableView+="request for tutor profile";
		    						 }else{
		    							 tableView+="N/A";
		    						 }
		    						 tableView+="</td><td>  <a href='javascript:updateUser("+response.result[index].userId+");'> Update</a> <a href='javascript:deleteUser("+response.result[index].userId+");'> Delete</a></td></tr>";
		    		   
		    	  } 
		    	  if(response.result.length>0){
		    		  tableView += "</table></div>";
		    		   
		    		  $('#searchResult').html(tableView);
		    		  $('#searchResult').show('slow');
		  		       
		    	  }else{
		    		  $('#searchResult').html("<h2>No Records Found.</h2>" + tableView);
		    		  $('#searchResult').show('slow');
		    	  }
		    			     
		    	
		      }else{
		    	  errorInfo = "";
		    	 /* for(i =0 ; i < response.result.length ; i++){
		    		  errorInfo += "<br>" + (i + 1) +". " + response.result[i].code;
		    	  }*/
		    	  //alert("Please correct following errors: " + errorInfo);
		    	  $('#info').html("Please correct following errors: " + errorInfo);
		           $('#info').show('slow');
		      }	      
		    },  
		    error: function(e){  
		      alert('Error: ' + e);  
		    }  
		  });  
		}  
	
	
 
</script>
</head>
	<div id="menu">
		<ul>	
			<li><a href="/onlinetutoring" title="home">HOME</a></li>
			<sec:authorize url="/questionPost*">
				<li><a href="/onlinetutoring/questionPost" title="post question">POST QUESTION</a></li>
		 	</sec:authorize>	
		 	<li><a href="/onlinetutoring/advancedSearchQuestion" title="advanced search">ADVANCED SEARCH</a></li>
		 	<li><a href="/onlinetutoring/viewProfile" title="view profile">VIEW PROFILE</a></li>
		 	<sec:authorize url="/admin*">
		 	<li><a class="current"  href="/onlinetutoring/admin/userMaintenance" title="admin">ADMIN</a></li>
			</sec:authorize>
		</ul>
	</div>

	<div id="main">
		<div id="right_side">
			 
		</div>
		<div id="left_side">
			<div class="intro">
			 <div class="pad">This is user Maintenance Page. </div> 
			 
			</div>
			<div class="mpart">
			 <table width="100%" border="0" cellpadiing="2" cellspacing="0">
			<tr>
				<td align="center">
					<%-- <%@ include file="../common/inc_BorderTop.jsp"%> --%>
					<table width="100%" border="0" cellpadiing="0" cellspacing="0">
						<tr>
							<td width="15%">
								<font class="NormalTXT_content">User Name</font>
							</td>
							<td width="25%">
								<input type="text" id="txtUsername"  maxlength="20" >
							</td>
							<td width="10%">
								<font class="NormalTXT_content">Role</font>
							</td>
							<td width="25%">
							<select id="selFRole" size="1" class="InputTXT_Long">
							 	<option value='ALL' selected="selected">-ALL-</option>
							  <c:forEach items="${roles}" var="role">
							 		 <option value="${role.roleId}">${role.role}</option>
							  </c:forEach>
							 </select>
								
								
							</td>
							
							
							
							<td align="right">
								<input type="button" id="btnSearch" class="Button" value="Find" onclick="searchUsers()">
							</td>
						</tr>
					</table>
					<%-- <%@ include file="../common/inc_BorderBottom.jsp"%> --%>
				</td>
			</tr>
			<tr>
				<td align="center">
					<%-- <%@ include file="../common/inc_BorderTop.jsp"%> --%>
					<table width="100%" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td>
								<h3><font class="SubHeaderTXT">Users</font></h3>
							</td>
						</tr>
						 
						<tr>
							<td style="height: 170px;" valign="top">
								<div id="searchResult">
								  <div id="spnData" class="CSSTableGenerator" >      
							          <table >
							               	 <tr>
							                     	<td>
							                           User Name
							                        </td>
							                        <td >
							                           Role
							                        </td>
							                        <td>
							                           Reputation Points
							                        </td>
							                         <td>
							                           Loyalty Points
							                        </td>
							                        <td>
							                           Pending Requests
							                        </td>
							                         <td>
							                           Action
							                        </td>
							                    </tr>
							                    
							                   <c:forEach items="${resUsers.result}" var="user" varStatus="status">
							                    <tr>
							                        <td >
							                            <a href="viewProfile/${user.userId}">${user.userName}</a>
							                        </td>
							                        <td>
							                           <select id="sel${user.userId}">
																<c:forEach items="${roles}" var="role">
																	 <option value="${role.roleId}" ${role.roleId == user.roleId.roleId ? 'selected' : ''}>${role.role}</option>
																 </c:forEach>
													 	</select>
														    
													 
   		
							                           
							                        </td>
							                         <td >
							                         	${user.roleId.roleId ==3 ? 'N/A' : user.reputation}
							                         </td>
							                        <td>
							                            ${user.loyality}
							                        </td>
							                        <td>
							                        	<%-- <c:if test="${user.is_pending ==1}">
							                          	 <span> request for tutor profile</span>
							                           </c:if> --%>
							                           ${user.is_pending ==1 ? 'request for tutor profile' : 'N/A'}
							                        </td>
							                        <td>
							                         <a href="javascript:updateUser(${user.userId});"> Update</a>
							                         <a href="javascript:deleteUser(${user.userId});"> Delete</a>
							                        </td>
							                    </tr>
							                    
							                    </c:forEach>
							                     
							                </table>
							     </div>
							    </div>		 
							</td>
						</tr>
					
						 
					</table>
					
					
					
					
				<%-- 	<%@ include file="../common/inc_BorderBottom.jsp"%> --%>
				</td>
			</tr>
			
		</table>
		<div id="info" class="success"> 
		</div>
		
	</div>
	<jsp:include page="include_footer.jsp" /> 
</div>

</body>
</html>


 