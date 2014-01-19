 package com.onlinetutoring.service;

import java.util.List;


import com.onlinetutoring.model.QuestionCategory;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.Tags;
import com.onlinetutoring.model.ViewQuestion;
import com.onlinetutoring.utills.Criteria;
 /**
 * 
 * QuestionsService.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:55:58 AM
 */
public interface QuestionsService {
	
	/**
	 * 
	 * @param question
	 */
	public void postQuestion(Questions question);
	/**
	 * 
	 * @param question
	 * Jan 13, 20142:35:06 PM
	 */
	public void updateQuestion(Questions question);
	/**
	 * 
	 * @param questionId
	 * @return
	 * Jan 13, 20142:35:08 PM
	 */
	public Questions getQuestionByQuestionId(Long questionId);
	/**
	 * 
	 * @param question
	 * Jan 13, 20142:35:11 PM
	 */
	public void deleteQuestion(Questions question); 
	/**
	 * 
	 * @return
	 */
	public List<Questions> getAllQuestions();
	 /**
	  * 
	  * @return
	  */
	public List<QuestionCategory> getAllQuestionCategories();
	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public List searchQuestions(Criteria criteria);
	/**
	 * 
	 * @param viewQuestion
	 */
	public void insertQuestionView(ViewQuestion viewQuestion);
	/**
	 * 
	 * @param questionid
	 * @param loggedinuserId
	 * @return
	 */
	public boolean searchQuestionView(Long questionid,Long loggedinuserId);
	/**
	 * 
	 * @param categoryID
	 * @return
	 */
	public List<QuestionCategory> findByQuestionCategoryId(Long categoryID);
	/**
	 * 
	 * @param tagName
	 * @return
	 */
	public List<Tags> findByTagsname(String tagName);
	/**
	 * 
	 * @param searchKey
	 * @return
	 */
	public List searchQuestions(String searchKey);
	 
}
 