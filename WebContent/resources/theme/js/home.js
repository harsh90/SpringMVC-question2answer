/**
 * 
 */
$(document).ready(function(){
 
		 
		 var obj = jQuery.parseJSON( modelAttributeValue );
		
		 if(obj!=null){
			  
			 if(obj.status=="SUCCESS"){
				 generateQuestionData(obj);
			 }
		 }
	  
});

function generateQuestionData(response){
	  
   	  userInfo = " <ul id='content'>";
   	  for(var index in response.result){
   		   
   		  userInfo +="<li><div id='q7886' class='qa-q-list-item'>"+
				"<div class='qa-q-item-stats'>"+
				"<span class='qa-a-count'>"+
				"<span class='qa-a-count-data'>"+response.result[index].noOfAnswers+"</span><span class='qa-a-count-pad'> answer</span>"+
				"</span>"+
				"</div>	<div class='qa-q-item-main'>"+
					"<span class='qa-view-count'>"+
					"<span class='qa-view-count-data'>"+response.result[index].views+"</span><span class='qa-view-count-pad'> views</span>"+
					
					"</span>	<div class='qa-q-item-title'>"+
					" <a href='viewQuestion/id/"+response.result[index].question_id+"'>"+
					"<span title=''>"+ response.result[index].question_title +"</span>"+
					" </a></div>	<span class='qa-q-item-meta'> Asked"+
					"<span class='qa-q-item-when'> <span class='qa-q-item-when-data'>"+ response.result[index].time_created +"</span><span class='qa-q-item-when-pad'> </span>"+
					"</span>"+
					"<span class='qa-q-item-who'>"+
					"<span class='qa-q-item-who-pad'> by </span>"+
					"<span class='qa-q-item-who-data'> <a class='qa-q-item-what' href='viewProfile/"+ response.result[index].author_id+"'>" + response.result[index].author_name +"  </a></span>"+
					"</span>	</span>"+
					"<div class='qa-q-item-tags'>TAGS : "+ response.result[index].tags +"</div>"+
					 "</div><div class='qa-q-item-clear'>"+
				"	</div></div> <!-- END qa-q-list-item --> <hr> </li>" ;
   	  	}
   		  if(response.result.length>0){
   		  userInfo += "</ul><div class='holder'></div>";
   		  $('#searchResult').html(userInfo);
 	    	   
 		      $('#searchResult').show('slow');
 			  	$("div.holder").jPages({
 				    containerID : "content",
 				    perPage: 5
 			  	});
	   	  }else{
	   		  $('#searchResult').html("<h2>No Records Found.</h2>" + userInfo);
	   		  $('#searchResult').show('slow');
	   	  }
	   		  
   	 
} 