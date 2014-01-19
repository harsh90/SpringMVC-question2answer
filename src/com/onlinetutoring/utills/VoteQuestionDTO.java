package com.onlinetutoring.utills;
/**
 * 
 * VoteQuestionDTO.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 11, 2014 2:38:17 AM
 */
public class VoteQuestionDTO {
	
	private Long voteId;
	private Long questionId;
	private Long votedby_id;
	private int status;
	
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getVotedby_id() {
		return votedby_id;
	}
	public void setVotedby_id(Long votedby_id) {
		this.votedby_id = votedby_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
 
	
	
}
