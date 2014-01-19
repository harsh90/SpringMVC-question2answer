package com.onlinetutoring.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinetutoring.dao.UserDao;
import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.utills.UserDTO;
import com.onlinetutoring.utills.VoteAnswerDTO;
 
/**
 * 
 * UserDaoImpl.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:54:14 AM
 */

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	public void AddUser(User user) {
		try{
			UserRole userRole=(UserRole)getCurrentSession().get(UserRole.class,user.getRoleId().getRoleId());
			user.setRoleId(userRole);
			getCurrentSession().save(user);
		}catch(Exception exception){
			System.out.println(exception.getLocalizedMessage());
		}
	}

	public void deleteUser(Long userId) {
		User user=(User)getCurrentSession().load(User.class, userId);
		
		getCurrentSession().delete(user);
		
	}

	public User searchUser(Long userId) {
		return (User)getCurrentSession().get(User.class, userId);
	}

	public void updateUser(User user) {
		getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		return ((List<User>)getCurrentSession().createCriteria(User.class).list());
	}

	public List<User> getUserByUsername(String userName) {
		return getCurrentSession().getNamedQuery("User.findByUserName").setString("userName", userName).list();
	}


	@Override
	public UserRole getUserRole(Long roleid) {
		return (UserRole)getCurrentSession().get(UserRole.class, roleid);
	}


	@Override
	public List<UserRole> getAllRoles() {
		return (List<UserRole>) getCurrentSession().createCriteria(UserRole.class).list();
	}


	@Override
	public List<UserDTO> getAllUsers(String userName, String roleId) {
		// TODO Auto-generated method stub
		final ArrayList<UserDTO> listUsers=new ArrayList<>();
		
		if(userName.equals("&")){
			userName="%";
		}else{
			userName="%"+userName+"%";
		}
		if(roleId.equals("ALL")){
			roleId="%";
		}else{
			roleId="%"+roleId+"%";
		}
		
		String sql="SELECT * FROM onlinetutoring.user WHERE user_name LIKE ?  AND roleId LIKE ?";
		 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
  
	  
 
		try {
			dbConnection = dataSource.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);
			 preparedStatement.setString( 1, userName);
			 preparedStatement.setString(2, roleId);
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			
			UserDTO  user=null;
			
			while (rs.next()) {
				user=new UserDTO();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setEmailAddress(rs.getString("email_address"));
				user.setPassword(rs.getString("password"));
				user.setCreatedDate(rs.getDate("createdDate"));
				user.setIsBanded(rs.getInt("is_banded"));
				user.setLoyality(rs.getLong("loyality"));
				user.setReputation(rs.getLong("reputation"));
				UserRole userRole=getUserRole(rs.getLong("roleId"));
			 	user.setRoleId(userRole.getRoleId());
			 	user.setRoleName(userRole.getRole());
			 	System.out.println(user.getRoleId());
				 
				
				
				listUsers.add(user);
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		
		 
		return listUsers;
	}


	@Override
	public List<User> findUserByEmail(String email) {
		return (List<User>)getCurrentSession().getNamedQuery("User.findByEmailAddress").setString("emailAddress", email).list();
	}

}
