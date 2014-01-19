package com.onlinetutoring.model;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * JsonResponse.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:33:24 PM
 */
public class JsonResponse {
	private String status = null;
	private Object result = null;
	
	private  Map<Long ,String> roles=new HashMap<>();
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Map<Long ,String> getRoles() {
		if(roles==null){
			roles=new HashMap<>();
		}
		return roles;
	}
	public void setRoles(Map<Long ,String> roles) {
		this.roles = roles;
	}
	
}
