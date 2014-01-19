package com.onlinetutoring.dao.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinetutoring.dao.AnswersDao;
import com.onlinetutoring.model.Answers;
/**
 * 
 * AnswersDaoImpl.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:32:27 PM
 */
@Repository("answersDao")
public class AnswersDaoImpl implements AnswersDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
  
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void postAnswer(Answers answer) {
		getCurrentSession().save(answer);
	}

	@Override
	public void updateAnswer(Answers answer) {
		getCurrentSession().update(answer);
	}

	@Override
	public Answers getAnswerByAnswerid(Long answerId) {
		return (Answers) getCurrentSession().get(Answers.class,answerId);
	}

	@Override
	public void deleteAnswer(Answers answer) {
		Answers answers=(Answers)getCurrentSession().load(Answers.class, answer);
		if(answers!=null){
			getCurrentSession().delete(answers);
		}
	}

	@Override
	public List<Answers> getAllAnswersByQuestionId(Long questionId) {
		return getCurrentSession().getNamedQuery("Answers.findByQuestionId").setLong("questionId", questionId).list();
	}

}
