package com.onlinetutoring.utills;

import java.util.Date;
/**
 * 
 * UserDTO.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:38:49 PM
 */
public class UserDTO {
	private Long userId;

	private String userName;

	private String emailAddress;

	private String password;

	private Date createdDate;

	private int isBanded;

	private int is_pending;

	private Long reputation;

	private Long loyality;

	private Long roleId;

	private String roleName;
	
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long long1) {
		this.roleId = long1;
	}

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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getIsBanded() {
		return isBanded;
	}

	public void setIsBanded(int isBanded) {
		this.isBanded = isBanded;
	}

	public int getIs_pending() {
		return is_pending;
	}

	public void setIs_pending(int is_pending) {
		this.is_pending = is_pending;
	}

	public Long getReputation() {
		return reputation;
	}

	public void setReputation(Long reputation) {
		this.reputation = reputation;
	}

	public Long getLoyality() {
		return loyality;
	}

	public void setLoyality(Long loyality) {
		this.loyality = loyality;
	}
 

}
