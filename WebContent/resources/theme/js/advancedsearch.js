/**
 * 
 */
$(document).ready(function(){
	$(".timeago").livequery(function() // LiveQuery 
	{
	$(this).timeago(); // Calling Timeago Funtion 
	});
	
	$("div.holder").jPages({
	    containerID : "content",
	    perPage: 5
  	});
});
function doAjaxGetSearch() {  
	  // get the form values  
	  var category = $('#categoryies').val();
	  var title = $('#title').val();
	  var question = $('#question').val();
	  var tags = $('#tags').val();
	  var sort = $('#sort').val();
	  if(category==""){
		  category="&";
	  }
	  if(title==""){
		  title="&";
	  }
	  if(question==""){
		  question="&";
	  }
	  if(tags==""){
		  tags="&";
	  }
	 
	  $.ajax({  
	    type: "GET",  
	    url: contexPath + '/search/category/' + category + '/title/' + title+'/question/' + question+'/tags/' + tags  + '/sort/' + sort,
	    dataType: 'json',
	    success: function(response){
	      // we have the response 
	    	///alert(response);
	    	
	      if(response.status == "SUCCESS"){
	    	  userInfo = " <ul id='content'>";
	    	  for(var index in response.result){
	    		  
	    		  // response.result[index].author_name
	    		  
	    		  
	    		  userInfo +="<li><div id='q7886' class='qa-q-list-item'>"+
					"<div class='qa-q-item-stats'>"+
					"<span class='qa-a-count'>"+
					"<span class='qa-a-count-data'>"+response.result[index].noOfAnswers+"</span><span class='qa-a-count-pad'> answer </span>"+
					"</span>"+
					"</div>	<div class='qa-q-item-main'>"+
						"<span class='qa-view-count'>"+
						"<span class='qa-view-count-data'>"+response.result[index].views+"</span><span class='qa-view-count-pad'> views </span>"+
						
						"</span>	<div class='qa-q-item-title'>"+
						" <a href='viewQuestion/id/"+response.result[index].question_id+"'>"+
						"<span title=''>"+ response.result[index].question_title +"</span>"+
						" </a></div>	<span class='qa-q-item-meta'> Asked  "+
						"<span class='qa-q-item-when'> <span class='qa-q-item-when-data'>"+ response.result[index].time_created +"</span><span class='qa-q-item-when-pad'> ago </span>"+
						"</span>"+
						"<span class='qa-q-item-who'>"+
						"<span class='qa-q-item-who-pad'> by </span>"+
						"<span class='qa-q-item-who-data'><a href='#' >"+ response.result[index].author_name +"</a></span>"+
						"</span>	</span>"+
						"<div class='qa-q-item-tags'>TAGS : "+ response.result[index].tags +"</div>"+
						 "</div><div class='qa-q-item-clear'>"+
					"	</div></div> <!-- END qa-q-list-item --> <hr> </li>" ;
	    		  
	    	  }
	    	  if(response.result.length>0){
	    		  userInfo += "</ul><div class='holder'></div>";
	    		  $('#searchResult').html("<h2>Search Results</h2>" + userInfo);
	  	    	/*  $('#categoryies').val('');
	  		      $('#title').val('');*/
	  		      $('#error').hide('slow');
	  		      $('#searchResult').show('slow');
	  			  	$("div.holder").jPages({
	  				    containerID : "content",
	  				    perPage: 5
	  			  	});
	    	  }else{
	    		  $('#searchResult').html("<h2>No Records Found.</h2>" + userInfo);
	    		  $('#searchResult').show('slow');
	    	  }
	    			     
	    	
	      }else{
	    	  errorInfo = "";
	    	 /* for(i =0 ; i < response.result.length ; i++){
	    		  errorInfo += "<br>" + (i + 1) +". " + response.result[i].code;
	    	  }*/
	    	  $('#error').html("Please correct following errors: " + errorInfo);
	    	  $('#searchResult').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      alert('Error: ' + e);  
	    }  
	  });  
	}  