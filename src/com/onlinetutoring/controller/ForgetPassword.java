package com.onlinetutoring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinetutoring.model.User;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.MailUtil;


@Controller
public class ForgetPassword {
	
	 @Autowired
	 private UserService userService;
	
	 /**
	  * 
	  * @param model
	  * @param principal
	  * @param request
	  * @return
	  * Jan 13, 201411:23:53 PM
	  */
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model, Principal principal,HttpServletRequest request  ) {
		 
	 
		return "forgetPassword";

	}
	 /**
	  * 
	  * @param model
	  * @param principal
	  * @param request
	  * @return
	  * Jan 13, 201411:23:46 PM
	  */
	@RequestMapping(value = "/processForgetPassword", method = RequestMethod.POST)
	public String processChangePassword(ModelMap model, Principal principal,HttpServletRequest request  ) {
		String message="";
	//	System.out.println(getLoggedInUser().getPassword());
		//User loggedinuser=getLoggedInUser();
		String emailAddress=request.getParameter("emailAddress");
			userService.findUserByEmail(emailAddress);
		 
		
		String[] recipients = new String[]{"youremail@yahoo.com"};  
		 String[] bccRecipients = new String[]{"youremail@gmail.com"};  
	     String subject = "Hi this is test Mail";  
	     String messageBody = "Test Mail from codesstore.blogspot.com";  
	  
	     new MailUtil().sendMail(recipients, bccRecipients, subject, messageBody);
	     
		
		
		 
		
		message="Old Password Does not match2";
		model.addAttribute("message",message);
		
		//model.addAttribute("username", name);
		//model.addAttribute("message", "Spring Security Custom Form example");
		return "changePassword";

	}
	
	/**
	 * 
	 * @return
	 * Jan 13, 20142:26:39 PM
	 */
	private User getLoggedInUser() {
		org.springframework.security.core.userdetails.User loggedInUser=null;
		try{ 
			loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 
		}catch(Exception e){
			 
		}
		
		 
		User logedInUser=null;
		if (loggedInUser != null) {
			List<User> loggedInUserDetails= userService.getUserByUsername(loggedInUser.getUsername());
			logedInUser=null;
			
			if(loggedInUserDetails.size()>0){
				  logedInUser =loggedInUserDetails.get(loggedInUserDetails.size()-1);
			}
		}
		return logedInUser;
	}
}
