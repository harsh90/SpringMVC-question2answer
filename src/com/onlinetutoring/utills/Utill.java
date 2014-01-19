package com.onlinetutoring.utills;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
/**
 * 
 * Utill.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:57:15 AM
 */
public class Utill {
	
	/**
	 * 
	 * @param securityContext
	 * @return
	 */
	public static String getCurrentLoggedInUserName( SecurityContext securityContext) {
		// TODO Auto-generated method stub
		// Authentication authentication = .getAuthentication();
		Authentication authentication = securityContext.getAuthentication();

		return authentication.getName();
	}
	
	public static String validateString(String string){
		if(string==null){
			return null;
		}
		return string.replace("'", "");
		
		
	}
	 
}
