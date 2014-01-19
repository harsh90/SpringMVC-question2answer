package com.onlinetutoring.validators;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * 
 * UserForm.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:57:24 AM
 */
public class UserForm {
	
	private Long userId;
	@NotEmpty
	@Size(min=1,max=100)
	private String userName;
	@NotEmpty
	@Size(min=1,max=100)
	@Email
	private String userEmailAddress;
	private boolean tutorProfile;
	@NotEmpty
	@Size(min=6,max=20)
	private String password;
	private String confirmPassword;
 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmailAddress() {
		return userEmailAddress;
	}
	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isTutorProfile() {
		return tutorProfile;
	}
	public void setTutorProfile(boolean tutorProfile) {
		this.tutorProfile = tutorProfile;
	}
 
	
	
	
}
