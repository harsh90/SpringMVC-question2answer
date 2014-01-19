package com.onlinetutoring.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetutoring.dao.AnswersDao;
import com.onlinetutoring.model.Answers;
import com.onlinetutoring.service.AnswerService;
/**
 * 
 * AnswersServiceImpl.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 9, 2014 1:09:54 AM
 */
@Component("answersService")
@Transactional
public class AnswersServiceImpl implements AnswerService{
	
	@Autowired
	private AnswersDao answersDao;
	 
	@Override
	public void postAnswer(Answers answer) {
		answersDao.postAnswer(answer);
	}

	@Override
	public void updateAnswer(Answers answer) {
		answersDao.updateAnswer(answer);
	}

	@Override
	public Answers getAnswerByAnswerid(Long answerId) {
		return answersDao.getAnswerByAnswerid(answerId);
	}

	@Override
	public void deleteAnswer(Answers answer) {
		  answersDao.deleteAnswer(answer);
	}

	@Override
	public List<Answers> getAllAnswersByQuestionId(Long questionId) {
		return answersDao.getAllAnswersByQuestionId(questionId);
	}

	 

}
