 package com.onlinetutoring.utills;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.onlinetutoring.model.QuestionCategory;

 /**
 * 
 * Constants.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:56:44 AM
 */ 
public class Constants {
	public static final String ANONYMOUS_USER="anonymousUser";
	public static final String GUEST_USER="guest";
	
	public static void main(String[] args) {
		JSONObject jsonObject=null;
		JSONArray jsonArray=new JSONArray();
		 List<QuestionCategory> questionCategories=new ArrayList<>();
		 
		 QuestionCategory obj1=new QuestionCategory();
		 obj1.setQuestionCategoryId((long)123);
		 obj1.setQuestionCategory("JAVA PROGRAMMING");
		 
		 QuestionCategory obj2=new QuestionCategory();
		 obj2.setQuestionCategoryId((long)1223);
		 obj2.setQuestionCategory("Music");
		 
		 questionCategories.add(obj1);
		 questionCategories.add(obj2);
 		for (QuestionCategory questionCategory : questionCategories) {
			jsonObject =new JSONObject();
			
			jsonObject.put("id", questionCategory.getQuestionCategoryId());
			jsonObject.put("value", questionCategory.getQuestionCategory());
			
			jsonArray.add(jsonObject);
		}
 		
 		 for(int i=0;i<jsonArray.size();i++){
 			 System.out.println(jsonArray.get(i).toString());
 		 }
 		 
 		 System.out.println("Executed");
	}
}
 