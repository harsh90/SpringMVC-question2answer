package com.onlinetutoring.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * Answers.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:54:34 AM
 */
@Entity
@Table(name = "answers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answers.findAll", query = "SELECT a FROM Answers a"),
    @NamedQuery(name = "Answers.findByAnswerId", query = "SELECT a FROM Answers a WHERE a.answerId = :answerId"),
    @NamedQuery(name = "Answers.findByQuestionId", query = "SELECT a FROM Answers a WHERE a.questionId = :questionId"),
    @NamedQuery(name = "Answers.findByAuthorId", query = "SELECT a FROM Answers a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "Answers.findByScore", query = "SELECT a FROM Answers a WHERE a.score = :score"),
    @NamedQuery(name = "Answers.findByText", query = "SELECT a FROM Answers a WHERE a.text = :text"),
    @NamedQuery(name = "Answers.findByTimeCreated", query = "SELECT a FROM Answers a WHERE a.timeCreated = :timeCreated"),
    @NamedQuery(name = "Answers.findByEditorId", query = "SELECT a FROM Answers a WHERE a.editorId = :editorId"),
    @NamedQuery(name = "Answers.findByTimeEdited", query = "SELECT a FROM Answers a WHERE a.timeEdited = :timeEdited")})
public class Answers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "score")
    private Long score;
    @Size(max = 2500)
    @Column(name = "text")
    private String text;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "editor_id")
    private Long editorId;
    @Column(name = "time_edited")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEdited;
    @JoinTable(name = "answer_votes", joinColumns = {
        @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")}, inverseJoinColumns = {
        @JoinColumn(name = "voted_by", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<User> userCollection;

    public Answers() {
    }

    public Answers(Long answerId) {
        this.answerId = answerId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answers)) {
            return false;
        }
        Answers other = (Answers) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.Answers[ answerId=" + answerId + " ]";
    }
    
}

