package com.onlinetutoring.dao;

import java.util.List;

import com.onlinetutoring.model.QuestionCategory;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.Tags;
import com.onlinetutoring.model.ViewQuestion;
import com.onlinetutoring.utills.Criteria;


 
 /**
  * 
  * QuestionsDao.java		
  * @author Harshitha de Silva
  * 
  * @since Jan 2, 2014 1:53:20 AM
  */
public interface QuestionsDao {
		/**
		 * 
		 * @return
		 * Jan 13, 20142:29:51 PM
		 */
 		public List<Questions> getAllQuestions();
		/**
		 * 
		 * @param questionId
		 * @return
		 * Jan 13, 20142:29:53 PM
		 */
		public Questions getQuestionByQuestionId(Long questionId);
	/**
	 * 
	 * @param question
	 * Jan 13, 20142:29:57 PM
	 */
		public void postQuestion(Questions question);
		/**
		 * 
		 * @param question
		 * Jan 13, 20142:30:00 PM
		 */
		public void deleteQuestion(Questions question);
		/**
		 * 
		 * @param question
		 * Jan 13, 20142:30:03 PM
		 */
		public void updateQuestion(Questions question);
		/**
		 * 
		 * @return
		 * Jan 13, 20142:30:08 PM
		 */
		public List<QuestionCategory> getAllQuestionCategories();
		/**
		 * 
		 * @param criteria
		 * @return
		 * Jan 13, 20142:30:10 PM
		 */
		public List searchQuestions(Criteria criteria);
		/**
		 * 
		 * @param viewQuestion
		 * Jan 13, 20142:30:14 PM
		 */
		public void insertQuestionView(ViewQuestion viewQuestion);
		/**
		 * 
		 * @param questionid
		 * @param loggedinuserId
		 * @return
		 * Jan 13, 20142:30:19 PM
		 */
		public boolean searchQuestionView(Long questionid,Long loggedinuserId);
		/**
		 * 
		 * @param categoryID
		 * @return
		 * Jan 13, 20142:30:21 PM
		 */
		public List<QuestionCategory> findByQuestionCategoryId(Long categoryID);
		/**
		 * 
		 * @param tagName
		 * @return
		 * Jan 13, 20142:30:25 PM
		 */
		public List<Tags> findByTagsname(String tagName);
		/**
		 * 
		 * @param searchKey
		 * @return
		 * Jan 13, 20142:30:30 PM
		 */
		public List searchQuestions(String searchKey);
}
