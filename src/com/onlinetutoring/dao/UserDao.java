package com.onlinetutoring.dao;

import java.util.List;

import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.utills.UserDTO;
/**
 * 
 * UserDao.java 	
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:53:34 AM
 */
public interface UserDao {
	/**
	 * 
	 * @param user
	 * Jan 13, 20142:30:43 PM
	 */
	public void AddUser(User user);
	/**
	 * 
	 * @param userId
	 * Jan 13, 20142:30:47 PM
	 */
	public void deleteUser(Long userId);
	/**
	 * 
	 * @param userId
	 * @return
	 * Jan 13, 20142:30:51 PM
	 */
	public User searchUser(Long userId);
	/**
	 * 
	 * @param user
	 * Jan 13, 20142:30:54 PM
	 */
	public void updateUser(User user);
	/**
	 * 
	 * @return
	 * Jan 13, 20142:30:57 PM
	 */
	public List<User> getAllUsers();
	/**
	 * 
	 * @param userName
	 * @param roleId
	 * @return
	 * Jan 13, 20142:31:01 PM
	 */
	public List<UserDTO> getAllUsers(String userName,String roleId);
	/**
	 * 
	 * @param userName
	 * @return
	 * Jan 13, 20142:31:04 PM
	 */
	public List<User> getUserByUsername(String userName);
	 /**
	  * 
	  * @param roleid
	  * @return
	  * Jan 13, 20142:31:08 PM
	  */
	public UserRole getUserRole(Long roleid);
	/**
	 * 
	 * @return
	 * Jan 13, 20142:31:12 PM
	 */
	public List<UserRole> getAllRoles();
	/**
	 * 
	 * @param email
	 * @return
	 * Jan 13, 201411:26:12 PM
	 */
	public List<User> findUserByEmail(String email);
}
