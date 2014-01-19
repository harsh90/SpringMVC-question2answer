package com.onlinetutoring.controller;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.Encription;
import com.onlinetutoring.validators.UserForm;
import com.onlinetutoring.validators.UserRegistrationValidator;


/**
 * 
 * RegistrationController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:52:25 AM
 */
@Controller
@RequestMapping("/userRegistration")
public class RegistrationController {
	 
	 
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRegistrationValidator userRegistrationValidator;
	
	
	/**
	 * 
	 * @param model
	 * @return
	 * Jan 13, 20142:28:43 PM
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String showRegistrationForm(Map<String, UserForm> model){
	 
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
			
		System.out.println("Authentication Name : "+authentication.getName());
		System.out.println("Authentication Name : "+authentication.getDetails().toString());
		
		/*if(!authentication.getName().equals(Constants.ANONYMOUS_USER)){
			return "/welcome";
		}
		*/
		UserForm userForm=new UserForm();
		
		model.put("userForm", userForm);
		
		
		
		return "userRegistration";
	}
	
	/**
	 * 
	 * @param userForm
	 * @param result
	 * @param map
	 * @return
	 * Jan 13, 20142:28:46 PM
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView processUserRegistration(@Valid UserForm userForm,BindingResult result,Map map){
		 
			userRegistrationValidator.validate(userForm, result);
		
		 if(result.hasErrors()){
		 	return new ModelAndView("/userRegistration");
		 }else{
			 
			User user=new User();
			
		 	user.setUserName(userForm.getUserName());
			user.setEmailAddress(userForm.getUserEmailAddress());
			user.setPassword(Encription.encriptPassword(userForm.getPassword()));
			user.setCreatedDate(new Date());
			user.setIsBanded(0);
			user.setReputation((long)0);
			user.setLoyality((long)0);
			
			if(userForm.isTutorProfile()){
				user.setIs_pending(1);
			}else{
				user.setIs_pending(0);
			}
			
			UserRole defaultRole=userService.getUserRole((long)3);
			user.setRoleId(defaultRole);
			
			userService.AddUser(user);
			 
		}
		return new ModelAndView("redirect:login");
	}
	
}
