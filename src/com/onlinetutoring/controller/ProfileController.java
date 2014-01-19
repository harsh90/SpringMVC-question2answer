package com.onlinetutoring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinetutoring.model.User;
import com.onlinetutoring.service.UserService;
/**
 * 
 * ProfileController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:26:54 PM
 */
@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @param model
	 * @return
	 * Jan 13, 20142:26:50 PM
	 */
	@RequestMapping(value="/viewProfile",method=RequestMethod.GET)
	public String viewProfile(Model model){
		System.out.println("Rendered unwanted file");
		User user =getLoggedInUser();
		if(user!=null){
			 
		
			User userData=userService.searchUser(user.getUserId());
			
			//userData.get
			boolean hasPrevilege=true;
			model.addAttribute("hasPrevilege",hasPrevilege);
			model.addAttribute("userData",userData);
		}
		return "viewProfile";
	}
	 /**
	  * 
	  * @param model
	  * @param userId
	  * @return
	  * Jan 13, 20142:26:46 PM
	  */
	@RequestMapping(value="admin/viewProfile/{userId}",method=RequestMethod.GET)
	public String viewProfileByAdmin(Model model,@PathVariable Long userId){
		
		//User user =getLoggedInUser();
		
		User user=userService.searchUser(userId);
		User loggedInUser=getLoggedInUser();
		boolean hasPrevilege=false;
		if(user!=null){
			if(loggedInUser!=null){
				if(loggedInUser.getUserId().equals(user.getUserId())){
					hasPrevilege=true;
				}
			}
			model.addAttribute("hasPrevilege",hasPrevilege);
			model.addAttribute("userData",user);
		}
		return "viewProfile";
	}
	/**
	 * 
	 * @param model
	 * @param userId
	 * @return
	 * Jan 13, 20142:27:11 PM
	 */
	@RequestMapping(value="viewProfile/{userId}",method=RequestMethod.GET)
	public String viewProfileByPublic(Model model,@PathVariable Long userId){
		
		//User user =getLoggedInUser();
		
		User user=userService.searchUser(userId);
		User loggedInUser=getLoggedInUser();
		boolean hasPrevilege=false;
		if(user!=null){
			if(loggedInUser!=null){
				if(loggedInUser.getUserId().equals(user.getUserId())){
					hasPrevilege=true;
				}
			}
			model.addAttribute("hasPrevilege",hasPrevilege);
			model.addAttribute("userData",user);
		}
		return "viewProfile";
	}
	/**
	 * 
	 * @return
	 * Jan 13, 20142:27:14 PM
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
