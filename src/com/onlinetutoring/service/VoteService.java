package com.onlinetutoring.service;

import java.util.Collection;

import com.onlinetutoring.model.VotingAnswer;
import com.onlinetutoring.model.VotingQuestion; 
import com.onlinetutoring.utills.VoteAnswerDTO;
import com.onlinetutoring.utills.VoteQuestionDTO;
/**
 * 
 * VoteService.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:36:48 PM
 */
public interface VoteService {
	/**
	 * 
	 * @param votingQuestion
	 */
	public void inserVote(VotingQuestion votingQuestion);
	/**
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
	/**
	 * 	
	 * @param questionId
	 * @param loggedInuserId
	 * @return
	 */
	public  Collection<VoteQuestionDTO> searchVotingQuestion(Long questionId,Long loggedInuserId);//VotingAnswer
	
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
