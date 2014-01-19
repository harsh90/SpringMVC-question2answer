package com.onlinetutoring.controller;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * RestController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:52:37 AM
 */

@Controller
@RequestMapping(value = "/MyData")
public class RestController {

	//private List<MyData> listMyData = new ArrayList<>();

/*@RequestMapping(value = "/{time}", method = RequestMethod.GET)
	public @ResponseBody
	String getMyData(@PathVariable long time ) {
		
	
		authenticateUser();

		JSONObject resultSet = null;
		System.out.println("REST GET METHOD CALLEDD>>>." + time);

		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < 10; i++) {
			try {

				resultSet = new JSONObject();

				resultSet.put("time", "ioo" + i);
				resultSet.put("message", "message" + i);

			} catch (JSONException e) {

				e.printStackTrace();
			}

			jsonArray.put(resultSet);
		}

		try {
			jsonObject.put("result", jsonArray);
			jsonObject.put("status", "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject.toString();
	}
*/
/*
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody
	MyData putMyData(@RequestBody MyData md) {
		System.out.println("REST PUT METHOD CALLED..");
		listMyData.add(md);
		return md;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	MyData postMyData() {
		System.out.println("REST POST METHOD CALLED ");

		return new MyData(System.currentTimeMillis(), "REST POST Call !!!");
	}

	@RequestMapping(value = "/{time}", method = RequestMethod.DELETE)
	public @ResponseBody
	MyData deleteMyData(@PathVariable long time) {
		System.out.println("REST DELETE METHOD CALLED");
		return new MyData(time, "REST DELETE Call !!!");
	}
*/
	
	private void authenticateUser() {
		// TODO Auto-generated method stub
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
			Collection<GrantedAuthority> name = authentication.getAuthorities();
					 for (GrantedAuthority grantedAuthority : name) {
						
						 System.out.println("User Name : "+grantedAuthority.getAuthority());
					 }
					 
	}

}
