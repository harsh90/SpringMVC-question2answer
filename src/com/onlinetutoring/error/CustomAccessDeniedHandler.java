package com.onlinetutoring.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
/**
 * 
 * CustomAccessDeniedHandler.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:33:04 PM
 */
public class CustomAccessDeniedHandler {
//implements AccessDeniedHandler {
	
	private String accessDeniedUrl;
	
	public CustomAccessDeniedHandler(){
		
	}
	


//	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
			
			System.out.println("My Custom aceess Denied handler method calledd... URL  "+accessDeniedUrl);
			response.sendRedirect(accessDeniedUrl);  
		    request.getSession().setAttribute("message",  
		  " Sorry user_dineshonjava You don't have privileges to view this page!!!"); 
		    

	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
	
	
	
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
	
}
