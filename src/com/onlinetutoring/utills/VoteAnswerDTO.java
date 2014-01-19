package com.onlinetutoring.utills;
/**
 * 
 * VoteAnswerDTO.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:38:59 PM
 */
public class VoteAnswerDTO {
	
	private Long voteId;
	private Long answerId;
	private Long votedby_id;
	private int status;
	
	public Long getVoteId() {
		return voteId;
	}
	public void setVoteId(Long voteId) {
		this.voteId = voteId;
	}
	public Long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
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
