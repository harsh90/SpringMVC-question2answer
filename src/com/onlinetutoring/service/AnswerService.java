package com.onlinetutoring.service;

import java.util.List;

import com.onlinetutoring.model.Answers;

/**
 * 
 * AnswerService.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:55:45 AM
 */
public interface AnswerService {
	/**
	 * 
	 * @param answer
	 */
	public void postAnswer(Answers answer)	;
	/**
	 * 
	 * @param answer
	 */
	public void updateAnswer(Answers answer);
	/**
	 * 
	 * @param answerId
	 * @return
	 */
	public Answers getAnswerByAnswerid(Long answerId);
	/**
	 * 
	 * @param answer
	 */
	public void deleteAnswer(Answers answer); 
	/**
	 * 
	 * @param questionId
	 * @return
	 */
	public List<Answers> getAllAnswersByQuestionId(Long questionId);
}
