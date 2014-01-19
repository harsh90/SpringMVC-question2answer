<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Create Account</title>

	<!---------- CSS ------------>
	 
	<link href="<c:url value="/resources/css/signupstyle.css"/>" rel="stylesheet"> 
</head>

<body>

    <!--BEGIN #signup-form -->
    <div id="signup-form">
        
        <!--BEGIN #subscribe-inner -->
        <div id="signup-inner">
        
        	<div class="clearfix" id="header">
        	
        		<img id="signup-icon" src="./images/signup.png" alt="" />
        
                <h1>Sign up to onlinetutoring.com </h1>

            
            </div>
			<p>Please complete the fields below.</p>

           <form:form action="/onlinetutoring/userRegistration" commandName="userForm" >
          
            	
                <p>

                <form:label path="userName" >User Name:</form:label>
					<FONT color="red"><form:errors path="userName" /></FONT>
					<form:input path="userName" size="26" />     
                </p>
                
                <p>
                 <form:label path="userEmailAddress">User Email:</form:label>
					<FONT color="red"><form:errors path="userEmailAddress" /></FONT>
					<form:input path="userEmailAddress" size="26" />
                </p>
                
                <p>

                <form:label path="password">Password:</form:label>
					<FONT color="red"><form:errors path="password" /></FONT>
					<form:password path="password" size="26" />
                </p>
                
                <p>
                <form:label path="confirmPassword">Confirm Password:</form:label>
                <form:password path="confirmPassword" size="26" />
                </p>
               	<div class="wrap">    
    				<label for="letter">
        				<form:checkbox path="tutorProfile" class="checkbox"/>
        					Request for tutor profile 
    				</label>
				</div>
                <br>
                  <input type="submit" value="Submit"/>
           </form:form>
             <a href="/onlinetutoring/login">Cancel</a>   
		<div id="required">
		<p> <br/>
		NOTE: You must login your account after sign up</p>
		</div>


            </div>
        
        <!--END #signup-inner -->
        </div>
        
    <!--END #signup-form -->   
    </div>

</body>
</html>
