package com.onlinetutoring.dao;

import java.util.List;

import com.onlinetutoring.model.Answers;
/**
 * 
 * AnswersDao.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:29:14 PM
 */
public interface AnswersDao {
	/**
	 * 
	 * @param answer
	 * Jan 13, 20142:29:26 PM
	 */
	public void postAnswer(Answers answer)	;
	/**
	 * 
	 * @param answer
	 * Jan 13, 20142:29:33 PM
	 */
	public void updateAnswer(Answers answer);
	/**
	 * 
	 * @param answerId
	 * @return
	 * Jan 13, 20142:29:37 PM
	 */
	public Answers getAnswerByAnswerid(Long answerId);
	/**
	 * 
	 * @param answer
	 * Jan 13, 20142:29:40 PM
	 */
	public void deleteAnswer(Answers answer); 
	/**
	 * 
	 * @param questionId
	 * @return
	 * Jan 13, 20142:29:45 PM
	 */
	public List<Answers> getAllAnswersByQuestionId(Long questionId);
	
}
