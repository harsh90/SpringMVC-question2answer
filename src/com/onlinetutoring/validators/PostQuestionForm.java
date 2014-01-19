package com.onlinetutoring.validators;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PostQuestionForm {
	
	private Long questionId;
	@NotEmpty
	@Size(min=1,max=100)
	private String title;
	@NotEmpty
	@Size(min=1,max=100)
	@Email
	private String question_description;
	@NotEmpty
	@Size(min=6,max=20)
	private String tags;
	
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestion_description() {
		return question_description;
	}
	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	
	
}
