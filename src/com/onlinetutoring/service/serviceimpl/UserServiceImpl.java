package com.onlinetutoring.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetutoring.dao.UserDao;
import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.UserDTO;
/**
 * 
 * UserServiceImpl.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:56:34 AM
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	
	@Autowired 
	private UserDao userDao;
	
	 
	public void AddUser(User user) {
		userDao.AddUser(user);
	}

	public void deleteUser(Long userId) {
		userDao.deleteUser(userId);
	}

	public User searchUser(Long userId) {
		return userDao.searchUser(userId);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public List<User> getUserByUsername(String userName) {
		return userDao.getUserByUsername(userName);
	}

	@Override
	public UserRole getUserRole(Long roleid) {
		return userDao.getUserRole(roleid);
	}

	@Override
	public List<UserRole> getAllRoles() {
		return userDao.getAllRoles();
	}

	@Override
	public List<UserDTO> getAllUsers(String userName, String roleId) {
		return userDao.getAllUsers(userName, roleId);
	}

	@Override
	public List<User> findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	 

}
