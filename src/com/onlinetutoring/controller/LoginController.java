package com.onlinetutoring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.onlinetutoring.model.User;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.Constants;
import com.onlinetutoring.utills.Encription;
/**
 * 
 * LoginController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:53:00 AM
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @param model
	 * @param principal
	 * @param request
	 * @return
	 * Jan 13, 20142:26:05 PM
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal,HttpServletRequest request  ) {
		Authentication authentication=null;
		String name =null;
		try{
			authentication= SecurityContextHolder.getContext().getAuthentication();
			name = principal.getName();
		}catch (Exception exception){
			System.out.println(exception.getLocalizedMessage());
		}
		 
		request.getSession().setAttribute("loggedInUser","Session MEssage e");
		System.out.println("Session Attribute SET ");
		
		
	 	model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		return "welcome";

	}

	
	/**
	 * 
	 * @param model
	 * @return
	 * Jan 13, 20142:26:09 PM
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		
		System.out.println("Send Login form to user");
		
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Authentication Name LOGIN : "+authentication.getName());
		authentication.getName();
	 	if(!authentication.getName().equals(Constants.ANONYMOUS_USER) && !authentication.getName().equals(Constants.GUEST_USER) ){
			return "home";
		} 
		
		return "login";

	}
	/**
	 * 
	 * @param model
	 * @return
	 * Jan 13, 20142:26:14 PM
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		System.out.println("Login error return to login");
		model.addAttribute("error", "true");
		return "login";

	}
	/**
	 * 
	 * @param model
	 * @return
	 * Jan 13, 20142:26:18 PM
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		System.out.println("Log Out method called .... returnn to index page");
		return "/home";
	}
	/**
	 * 
	 * @param model
	 * @param principal
	 * @return
	 * Jan 13, 20142:26:22 PM
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal principal) {
	
		System.out.println("accessDenied custom error pages..");
		
		String username = principal.getName();
		model.addAttribute("message", "Sorry " + username
				+ " You don't have privileges to view this page!!!");
		return "403";
	}
	/**
	 * 
	 * @param model
	 * @param principal
	 * @param request
	 * @return
	 * Jan 13, 20142:26:27 PM
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model, Principal principal,HttpServletRequest request  ) {
		 
		//System.out.println(getLoggedInUser().getPassword());
		
		//model.addAttribute("username", name);
		//model.addAttribute("message", "Spring Security Custom Form example");
		return "changePassword";

	}
	/**
	 * 
	 * @param model
	 * @param principal
	 * @param request
	 * @return
	 * Jan 13, 20142:26:34 PM
	 */
	@RequestMapping(value = "/processChangePassword", method = RequestMethod.POST)
	public ModelAndView processChangePassword(ModelMap model, Principal principal,HttpServletRequest request  ) {
		String message="";
		System.out.println(getLoggedInUser().getPassword());
		User loggedinuser=getLoggedInUser();
		
		if(loggedinuser!=null){
			String OldPassword = request.getParameter("OldPassword");
			String newPass = request.getParameter("newpassword");
			String conPass = request.getParameter("conpassword");
			
			if(OldPassword!=null && newPass!=null && conPass!=null){
				
				if(Encription.encriptPassword(OldPassword).equals(loggedinuser.getPassword())){
					if(newPass.equals(conPass)){
						loggedinuser.setPassword(Encription.encriptPassword(newPass));
						userService.updateUser(loggedinuser);
						
						message="password successfully changed";
						model.addAttribute("message",message);
						
						ModelAndView modelAndView = new ModelAndView(new RedirectView("/success",true));
						modelAndView.addObject("message",message);
						
						
						return modelAndView;
					}
				}
			}else{
				message="Old Password Does not match";
				model.addAttribute("message",message);
				ModelAndView modelAndView = new ModelAndView(new RedirectView("/changePassword",true));
				modelAndView.addObject("message",message);
				
				return modelAndView;
			}
		}
		
		message="Old Password Does not match2";
		//model.addAttribute("message",message);
		
		model.addAttribute("message",message);
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/changePassword",true));
		modelAndView.addObject("message",message);
		
		return modelAndView;

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
