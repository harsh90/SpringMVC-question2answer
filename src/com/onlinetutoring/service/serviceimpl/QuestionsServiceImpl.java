 package com.onlinetutoring.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetutoring.dao.QuestionsDao;
import com.onlinetutoring.model.QuestionCategory;
import com.onlinetutoring.model.Questions;
import com.onlinetutoring.model.Tags;
import com.onlinetutoring.model.ViewQuestion;
import com.onlinetutoring.service.QuestionsService;
import com.onlinetutoring.utills.Criteria;
 /**
  * 
  * QuestionsServiceImpl.java		
  * @author Harshitha de Silva
  * 
  * @since Jan 2, 2014 1:56:24 AM
  */ 
@Service("questionsService")
@Transactional
public class QuestionsServiceImpl implements QuestionsService {
	
	
	@Autowired
	private QuestionsDao questionsDao;
	

	public List<Questions> getAllQuestions() {
		return questionsDao.getAllQuestions();
	}
	
	public Questions getQuestionByQuestionId(Long questionId) {
		return questionsDao.getQuestionByQuestionId(questionId);
	}

	public void postQuestion(Questions question) {
	
		questionsDao.postQuestion(question);
	}
 
	public void deleteQuestion(Questions question) {
		questionsDao.deleteQuestion(question);
	}

	public void updateQuestion(Questions question) {
		questionsDao.updateQuestion(question);
	}

	@Override
	public List<QuestionCategory> getAllQuestionCategories() {
		return questionsDao.getAllQuestionCategories();
	}

	@Override
	public List searchQuestions(Criteria criteria) {
		return questionsDao.searchQuestions(criteria);
	}

	@Override
	public void insertQuestionView(ViewQuestion viewQuestion) {
		questionsDao.insertQuestionView(viewQuestion);
		
	}

	@Override
	public boolean searchQuestionView(Long questionid, Long loggedinuserId) {
		return questionsDao.searchQuestionView(questionid, loggedinuserId);
	}

	@Override
	public List<QuestionCategory> findByQuestionCategoryId(Long categoryID) {
		return questionsDao.findByQuestionCategoryId(categoryID);
	}

	@Override
	public List<Tags> findByTagsname(String tagName) {
		return questionsDao.findByTagsname(tagName);
	}

	@Override
	public List searchQuestions(String searchKey) {
		// TODO Auto-generated method stub
		return questionsDao.searchQuestions(searchKey);
	}

	 
	 
}
