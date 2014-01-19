package com.onlinetutoring.utills;

import java.util.List;
 
/**
 * 
 * QuestionDTO.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:38:35 PM
 */
public class QuestionDTO {
	private String question_id;
	private String question_title;
	private String question_description;
	private String author_id;
	private String author_name;
	private String time_created;
	private String editor_id ;
	private String editor_name;
	private String time_edited;
	private String is_closed;
	private String category_id;
	private String category_name;
	private Long score;
	private Long  views;
	private Long  noOfAnswers;
	private String tags;
	
	
	private List<AnswersDTO> answers;
	
	

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<AnswersDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswersDTO> answers) {
		this.answers = answers;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getEditor_name() {
		return editor_name;
	}
	public void setEditor_name(String editor_name) {
		this.editor_name = editor_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_description() {
		return question_description;
	}
	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}
	public String getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getTime_created() {
		return time_created;
	}
	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}
	public String getEditor_id() {
		return editor_id;
	}
	public void setEditor_id(String editor_id) {
		this.editor_id = editor_id;
	}
	public String getTime_edited() {
		return time_edited;
	}
	public void setTime_edited(String time_edited) {
		this.time_edited = time_edited;
	}
	public String getIs_closed() {
		return is_closed;
	}
	public void setIs_closed(String is_closed) {
		this.is_closed = is_closed;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long long1) {
		this.score = long1;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public Long getNoOfAnswers() {
		return noOfAnswers;
	}
	public void setNoOfAnswers(Long noOfAnswers) {
		this.noOfAnswers = noOfAnswers;
	}
 
	
}
