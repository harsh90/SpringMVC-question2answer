package com.onlinetutoring.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onlinetutoring.dao.VoteDao;
import com.onlinetutoring.model.VotingAnswer;
import com.onlinetutoring.model.VotingQuestion;
import com.onlinetutoring.utills.VoteAnswerDTO;
import com.onlinetutoring.utills.VoteQuestionDTO;
/**
 * 
 * VoteDaoImpl.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 11, 2014 2:34:41 AM
 */
@Repository("voteDao")
public class VoteDaoImpl implements VoteDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DataSource dataSource;
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	 
	@Override
	public void inserVote(VotingQuestion votingQuestion) {
		 getCurrentSession().save(votingQuestion);
	}

	@Override
	public Collection<VoteQuestionDTO> searchVotingQuestion(Long questionId, Long loggedInuserId) {
		
		final ArrayList<VoteQuestionDTO> votingQuestions=new ArrayList<>();
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String sql = "SELECT * FROM onlinetutoring.voting_question WHERE question_id=? and user_id=? ";
	  
 
		try {
			dbConnection = dataSource.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setLong( 1, questionId);
			preparedStatement.setLong(2, loggedInuserId);
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			
			VoteQuestionDTO  dto=null;
			
			while (rs.next()) {
				dto=new VoteQuestionDTO();
				dto.setVoteId(rs.getLong("vote_id"));
				dto.setQuestionId(rs.getLong("question_id"));
				dto.setVotedby_id(rs.getLong("user_id"));
				dto.setStatus(rs.getInt("status"));
				 
				votingQuestions.add(dto);
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		
		
		return votingQuestions;
       
		 
	 
	}

	@Override
	public VotingQuestion searchQuestionVote(Long voteId) {
		return (VotingQuestion) getCurrentSession().get(VotingQuestion.class,voteId);
	}

	@Override
	public void updateQuestionVote(VotingQuestion votingQuestion) {
		getCurrentSession().update(votingQuestion);
	}

	@Override
	public void inserVote(VotingAnswer votingAnswer) {
		getCurrentSession().save(votingAnswer);
	}

	@Override
	public VotingAnswer searchAnswerVote(Long voteId) {
		return (VotingAnswer) getCurrentSession().get(VotingAnswer.class, voteId);
	}

	@Override
	public void updateAnswerVote(VotingAnswer VotingAnswer) {
		getCurrentSession().update(VotingAnswer);
	}

	@Override
	public Collection<VoteAnswerDTO> searchVotingAnswer(Long answerId,
			Long loggedInuserId) {
		
		final ArrayList<VoteAnswerDTO> votingAnswers=new ArrayList<>();
		
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String sql = "SELECT * FROM onlinetutoring.voting_answer WHERE answer_id=? AND user_id=? ";
	  
 
		try {
			dbConnection = dataSource.getConnection();
			
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setLong( 1, answerId);
			preparedStatement.setLong(2, loggedInuserId);
			
			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			
			
			VoteAnswerDTO  dto=null;
			
			while (rs.next()) {
				dto=new VoteAnswerDTO();
				
				dto.setVoteId(rs.getLong("vote_id"));
				dto.setAnswerId(rs.getLong("answer_id"));
				dto.setVotedby_id(rs.getLong("user_id"));
				dto.setStatus(rs.getInt("status"));
				
				votingAnswers.add(dto);
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		} finally {
 
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
		}
		
		
		return votingAnswers;
	}
	
	

}
