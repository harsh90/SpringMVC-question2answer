package com.onlinetutoring.dao;

import java.util.Collection;

import com.onlinetutoring.model.VotingAnswer;
import com.onlinetutoring.model.VotingQuestion; 
import com.onlinetutoring.utills.VoteAnswerDTO;
import com.onlinetutoring.utills.VoteQuestionDTO;
/**
 * 
 * VoteDao.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:31:17 PM
 */
public interface VoteDao {
	/**
	 * 
	 * @param votingQuestion
	 */
	public void inserVote(VotingQuestion votingQuestion);
	/**
	 * 
	 * @param questionId
	 * @param loggedInuserId
	 * @return
	 */
	public  Collection<VoteQuestionDTO> searchVotingQuestion(Long questionId,Long loggedInuserId);
	/**
	 * 
	 * 
	 * @param voteId
	 * @return
	 */
	public VotingQuestion searchQuestionVote(Long voteId);
	/**
	 * 
	 * @param votingQuestion
	 */
	public void updateQuestionVote(VotingQuestion votingQuestion);
	
	//vote service classes
	/**
	 * 
	 * @param votingAnswer
	 */
	public void inserVote(VotingAnswer votingAnswer);
	/**
	 * 
	 * @param voteId
	 * @return
	 */
	public VotingAnswer searchAnswerVote(Long voteId);
	/**
	 * 
	 * @param VotingAnswer
	 */
	public void updateAnswerVote(VotingAnswer VotingAnswer);
	/**
	 * 
	 * @param answerId
	 * @param loggedInuserId
	 * @return
	 */
	public  Collection<VoteAnswerDTO> searchVotingAnswer(Long answerId,Long loggedInuserId);//VotingAnswer
}
