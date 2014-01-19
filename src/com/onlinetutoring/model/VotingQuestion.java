package com.onlinetutoring.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * VotingQuestion.java		
 * @author  Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:34:17 PM
 */
@Entity
@Table(name = "voting_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotingQuestion.findAll", query = "SELECT v FROM VotingQuestion v"),
    @NamedQuery(name = "VotingQuestion.findByVoteId", query = "SELECT v FROM VotingQuestion v WHERE v.voteId = :voteId"),
    @NamedQuery(name = "VotingQuestion.findByUserId", query = "SELECT v FROM VotingQuestion v WHERE v.userId = :userId"),
    @NamedQuery(name = "VotingQuestion.findByStatus", query = "SELECT v FROM VotingQuestion v WHERE v.status = :status")})
public class VotingQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "vote_id")
    private Long voteId;
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @ManyToOne
    private Questions questionId;
    public VotingQuestion() {
    }

    public VotingQuestion(Long voteId) {
        this.voteId = voteId;
    }

    public VotingQuestion(Long voteId, int status) {
        this.voteId = voteId;
        this.status = status;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Questions getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Questions questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voteId != null ? voteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotingQuestion)) {
            return false;
        }
        VotingQuestion other = (VotingQuestion) object;
        if ((this.voteId == null && other.voteId != null) || (this.voteId != null && !this.voteId.equals(other.voteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.myapp.models.VotingQuestion[ voteId=" + voteId + " ]";
    }
    
}
