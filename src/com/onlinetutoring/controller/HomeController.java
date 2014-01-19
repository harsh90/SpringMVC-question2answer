package com.onlinetutoring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlinetutoring.model.JsonResponse;
import com.onlinetutoring.model.User;
import com.onlinetutoring.service.QuestionsService;
import com.onlinetutoring.service.UserService;
import com.onlinetutoring.utills.Criteria;
import com.onlinetutoring.utills.QuestionDTO;
/**
 * 
 * HomeController.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:52:46 AM
 */
@Controller
public class HomeController {
	@Autowired
	private QuestionsService questionsService;
	
	@Autowired
	private UserService userService;
	 /**
	  * 
	  * @param map
	  * @return
	  * Jan 13, 20142:25:51 PM
	  */
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String initHome(ModelMap map ){
		System.out.println("Came to initHome  ");
		Criteria criteria=new Criteria();
		criteria.setCategory("ALL");
		List<QuestionDTO> dtos=questionsService.searchQuestions(criteria);
		
		
		for (int i=0;i<dtos.size();i++) {
			
			QuestionDTO questionDTO=dtos.get(i);
	 
			
			if(questionDTO.getAuthor_id()!=null){
				User user=userService.searchUser(Long.valueOf(questionDTO.getAuthor_id()));
				questionDTO.setAuthor_name(user.getUserName());
			}
			
			 
			if(questionDTO.getEditor_id()!=null){
				User editor=userService.searchUser(Long.valueOf(questionDTO.getEditor_id()));
				questionDTO.setEditor_name(editor.getUserName());
			}
			  
		} 
		 System.out.println("RESULT COUNT"+dtos.size());
		JsonResponse jsonResponse=new JsonResponse();
		
		 
		jsonResponse.setResult(dtos);
		jsonResponse.setStatus( "SUCCESS");
		
		JSONObject obj=JSONObject.fromObject(jsonResponse);
		map.addAttribute("data", obj);
		return "home";
	}
	 
	 /**
	  * 
	  * @param request
	  * @param map
	  * @return
	  * Jan 13, 20142:25:56 PM
	  */
	@RequestMapping(value="/searchHeaderQuestions",method=RequestMethod.GET)
	public String searchHeaderQuestions(HttpServletRequest request,ModelMap map){
		
		String searchKey=request.getParameter("tfq2b");
		System.out.println("searchKey"+searchKey);
		List<QuestionDTO> dtos=questionsService.searchQuestions(searchKey);
		for (int i=0;i<dtos.size();i++) {
			
			QuestionDTO questionDTO=dtos.get(i);
	 
			
			if(questionDTO.getAuthor_id()!=null){
				User user=userService.searchUser(Long.valueOf(questionDTO.getAuthor_id()));
				questionDTO.setAuthor_name(user.getUserName());
			}
			
			 
			if(questionDTO.getEditor_id()!=null){
				User editor=userService.searchUser(Long.valueOf(questionDTO.getEditor_id()));
				questionDTO.setEditor_name(editor.getUserName());
			}
			  
		} 
		 System.out.println("RESULT COUNT"+dtos.size());
		JsonResponse jsonResponse=new JsonResponse();
		
		 
		jsonResponse.setResult(dtos);
		jsonResponse.setStatus( "SUCCESS");
		
		JSONObject obj=JSONObject.fromObject(jsonResponse);
		map.addAttribute("data", obj);
		 
		return "home";
	}

}
