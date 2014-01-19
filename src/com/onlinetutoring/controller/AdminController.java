package com.onlinetutoring.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinetutoring.model.JsonResponse;
import com.onlinetutoring.model.User;
import com.onlinetutoring.model.UserRole;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.UserDTO;
@RequestMapping(value="/admin*")
@Controller
public class AdminController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userMaintenance")
	public String  setUpForm(Model map){
		
		List<User> users=userService.getAllUsers();
		
		JsonResponse jsonResponse=new JsonResponse();
		jsonResponse.setResult(users);
		
		map.addAttribute("resUsers",jsonResponse);
		map.addAttribute("roles",userService.getAllRoles());
		return "userMaintenance";
		
	}
	 
	//searchUsers/username/' + userName + '/roleId/' + roleId
	@RequestMapping(value="/searchUsers/username/{userName}/roleId/{roleId}",method=RequestMethod.GET)
	public @ResponseBody JsonResponse searchUsers(Model map,@PathVariable String userName ,	@PathVariable String  roleId){
		
		List<UserDTO> users=userService.getAllUsers(userName, roleId);
		
		for (UserDTO userDTO : users) {
			System.out.println("%%%%%%%%%%%%%% results "+userDTO.getUserId());
		}
		JsonResponse jsonResponse=new JsonResponse();

		List<UserRole> roles=userService.getAllRoles();
		for(UserRole role:roles){
			jsonResponse.getRoles().put(role.getRoleId(), role.getRole());
		}
		jsonResponse.setResult(users);
		jsonResponse.setStatus("SUCCESS"); 
		
		return jsonResponse;
		
	}
	
	///admin/updateUser

	@RequestMapping(value="/updateUser",method = RequestMethod.PUT,headers="Accept=application/json")
	public @ResponseBody JsonResponse updateUser(@RequestBody JSONObject jsonResponse) {
		System.out.println("REST PUT METHOD CALLED..");
		//listMyData.add(md);
		String roleId=(String)jsonResponse.get("roleId");
		Integer userId=(Integer)jsonResponse.get("userId");
		String searchUserName=(String)jsonResponse.get("searchUserName");
		String searchRoleId=(String)jsonResponse.get("searchRoleId");
		
		  
		if(userId!=null){
			User user=userService.searchUser(Long.valueOf(userId));
			
			UserRole newUserRole=userService.getUserRole(Long.valueOf(roleId));
			user.setRoleId(newUserRole);
			
			userService.updateUser(user);
		}
		
		List<UserDTO> users=userService.getAllUsers(searchUserName, searchRoleId);
		
		for (UserDTO userDTO : users) {
			System.out.println("%%%%%%%%%%%%%% results "+userDTO.getUserId());
		}
		JsonResponse response=new JsonResponse();

		List<UserRole> roles=userService.getAllRoles();
		for(UserRole role:roles){
			response.getRoles().put(role.getRoleId(), role.getRole());
		}
		response.setResult(users);
		response.setStatus("SUCCESS"); 
		
		
		return response;
	} 
	
	
	@RequestMapping(value = "deleteUser/{userId}", method = RequestMethod.DELETE,headers="Accept=application/json")
	public @ResponseBody JsonResponse deleteUser(@PathVariable long userId) {
		System.out.println("REST DELETE METHOD CALLED");
		
		userService.deleteUser(userId);
		
		JsonResponse response=new JsonResponse();
		
		response.setStatus("SUCCESS");
		
		return response;
	}

}
