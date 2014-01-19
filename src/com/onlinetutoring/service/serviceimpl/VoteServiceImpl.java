package com.onlinetutoring.service.serviceimpl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetutoring.dao.VoteDao;
import com.onlinetutoring.model.VotingAnswer;
import com.onlinetutoring.model.VotingQuestion;
import com.onlinetutoring.service.VoteService;
import com.onlinetutoring.utills.VoteAnswerDTO;
import com.onlinetutoring.utills.VoteQuestionDTO;
/**
 * 
 * VoteServiceImpl.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 11, 2014 4:34:20 PM
 */
@Service("voteService")
@Transactional
public class VoteServiceImpl implements VoteService{

	@Autowired
	private VoteDao voteDao;
	
	@Override
	public void inserVote(VotingQuestion votingQuestion) {
		voteDao.inserVote(votingQuestion);
	}

	@Override
	public Collection<VoteQuestionDTO> searchVotingQuestion(Long questionId, 
			Long loggedInuserId) {
		return voteDao.searchVotingQuestion(questionId, loggedInuserId);
	}

	@Override
	public VotingQuestion searchQuestionVote(Long voteId) {
		return voteDao.searchQuestionVote(voteId);
	}

	@Override
	public void updateQuestionVote(VotingQuestion votingQuestion) {
		voteDao.updateQuestionVote(votingQuestion);
	}

	@Override
	public void inserVote(VotingAnswer votingAnswer) {
		voteDao.inserVote(votingAnswer);
	}

	@Override
	public VotingAnswer searchAnswerVote(Long voteId) {
		return voteDao.searchAnswerVote(voteId);
	}

	@Override
	public void updateAnswerVote(VotingAnswer VotingAnswer) {
		voteDao.updateAnswerVote(VotingAnswer);
	}

	@Override
	public Collection<VoteAnswerDTO> searchVotingAnswer(Long answerId,
			Long loggedInuserId) {
		return voteDao.searchVotingAnswer(answerId, loggedInuserId);
	}
	
	

}
