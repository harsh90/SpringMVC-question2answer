package com.onlinetutoring.service;

import java.util.List;

import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.utills.UserDTO;
import com.onlinetutoring.validators.UserRegistrationValidator;

/**
 * 
 * UserService.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:56:04 AM
 */
public interface UserService {
	/**
	 * 
	 * @param user
	 */
	public void AddUser(User user);
	/**
	 * 
	 * @param userId
	 */
	public void deleteUser(Long userId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public User searchUser(Long userId);
	/**
	 * 
	 * @param user
	 */
	public void updateUser(User user);
	/**
	 * 
	 */
	public List<User> getAllUsers();
	/**
	 * 
	 * @param userName
	 * @param roleId
	 * @return
	 */
	public List<UserDTO> getAllUsers(String userName,String roleId);
	/**
	 * 
	 * @param userName
	 * @return
	 */
	public List<User> getUserByUsername(String userName);
	/**
	 * 
	 * @param roleid
	 * @return
	 */
	public UserRole getUserRole(Long roleid);
	/**
	 * 
	 * @return
	 */
	public List<UserRole> getAllRoles();
	
	public List<User> findUserByEmail(String email);
	
}
