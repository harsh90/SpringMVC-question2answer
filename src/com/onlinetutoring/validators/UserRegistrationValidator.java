package com.onlinetutoring.validators;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.onlinetutoring.model.User;
import com.onlinetutoring.service.UserService;

/**
 * 
 * UserRegistrationValidator.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:57:34 AM
 */
@Component("userRegistrationValidator")
public class UserRegistrationValidator {
	
	@Autowired
	private UserService userService;	
	
	public boolean supports(Class<?> class1){
		return UserForm.class.isAssignableFrom(class1);
	}
	
	public void validate(Object target,Errors errors){
		UserForm userForm=(UserForm) target;
		
		
	//	ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "NotEmpty.userForm.phoneNumber", "Phone number must not be empty");
		
		///check user email errors
		if(userForm.getUserEmailAddress().length()>0){
			userForm.setUserEmailAddress(userForm.getUserEmailAddress().trim());
			
			Pattern p=Pattern.compile(".+@.+\\.[a-z]+");
			Matcher matcher=p.matcher(userForm.getUserEmailAddress());
			boolean status=matcher.matches();
			
			if(status!=true){
				errors.rejectValue("userEmailAddress","UserEmail.is.not.valid","User email is not a well formed email address");
			}
			
			
		}
		System.out.println(userForm.getUserName());
		//check user name duplicates
		 List<User> userList=userService.getUserByUsername(userForm.getUserName());
		
		if(userList!=null && userList.size()>0){
			for (User user : userList) {
				System.out.println("FOUND USER ALREADY IN DATABASE " + user.getUserName());
			}
			
			errors.rejectValue("userName","matchUseName.userForm.userName","User name already Exsists");
		} 
		if(!userForm.getPassword().equals(userForm.getConfirmPassword())){
			errors.rejectValue("password","matchpassword.userForm.password","password and confirm password do not match");
		}
		
	}
}	
