 package com.onlinetutoring.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
 /**
 * 
 * Question.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:54:54 AM
 */ 
@Entity
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"),
    @NamedQuery(name = "Questions.findByQuestionId", query = "SELECT q FROM Questions q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Questions.findByQuestionTitle", query = "SELECT q FROM Questions q WHERE q.questionTitle = :questionTitle"),
    @NamedQuery(name = "Questions.findByQuestionDescription", query = "SELECT q FROM Questions q WHERE q.questionDescription = :questionDescription"),
    @NamedQuery(name = "Questions.findByAuthorId", query = "SELECT q FROM Questions q WHERE q.authorId = :authorId"),
    @NamedQuery(name = "Questions.findByTimeCreated", query = "SELECT q FROM Questions q WHERE q.timeCreated = :timeCreated"),
    @NamedQuery(name = "Questions.findByEditorId", query = "SELECT q FROM Questions q WHERE q.editorId = :editorId"),
    @NamedQuery(name = "Questions.findByTimeEdited", query = "SELECT q FROM Questions q WHERE q.timeEdited = :timeEdited"),
    @NamedQuery(name = "Questions.findByIsClosed", query = "SELECT q FROM Questions q WHERE q.isClosed = :isClosed"),
    @NamedQuery(name = "Questions.findByScore", query = "SELECT q FROM Questions q WHERE q.score = :score")})
public class Questions implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Long questionId;
    
    @Size(max = 125)
    @Column(name = "question_title")
    private String questionTitle;
    
    @Size(max = 1024)
    @Column(name = "question_description")
    private String questionDescription;
    
    @Column(name = "author_id")
    private Long authorId;
    
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    
    @Column(name = "editor_id")
    private Long editorId;
    
    @Size(max = 45)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_edited")
    private Date timeEdited;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_closed")
    private int isClosed;
    
    @Column(name = "score")
    private Long score;
    
    @Column(name = "views")
    private Long views;
    
    
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="question_tags", joinColumns={@JoinColumn(name="question_id")},inverseJoinColumns={@JoinColumn(name="tag_id")})
    private Collection<Tags> tagsCollection;
    
    
    
    @JoinColumn(name = "category_id", referencedColumnName = "question_category_id")
    @ManyToOne(optional = false)
    private QuestionCategory categoryId;

    public Questions() {
    }

    public Questions(Long questionId) {
        this.questionId = questionId;
    }

    public Questions(Long questionId, int isClosed) {
        this.questionId = questionId;
        this.isClosed = isClosed;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Long getEditorId() {
        return editorId;
    }

    public void setEditorId(Long editorId) {
        this.editorId = editorId;
    }

    public Date getTimeEdited() {
        return timeEdited;
    }

    public void setTimeEdited(Date timeEdited) {
        this.timeEdited = timeEdited;
    }

    public int getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(int isClosed) {
        this.isClosed = isClosed;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @XmlTransient
    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }
 
    public QuestionCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(QuestionCategory categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.Questions[ questionId=" + questionId + " ]";
    }

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}
    
}
