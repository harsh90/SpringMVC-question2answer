package com.onlinetutoring.model;

import java.io.Serializable;
import java.math.BigInteger;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * VotingAnswer.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 13, 2014 2:34:03 PM
 */
@Entity
@Table(name = "voting_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VotingAnswer.findAll", query = "SELECT v FROM VotingAnswer v"),
    @NamedQuery(name = "VotingAnswer.findByVoteId", query = "SELECT v FROM VotingAnswer v WHERE v.voteId = :voteId"),
    @NamedQuery(name = "VotingAnswer.findByUserId", query = "SELECT v FROM VotingAnswer v WHERE v.userId = :userId"),
    @NamedQuery(name = "VotingAnswer.findByStatus", query = "SELECT v FROM VotingAnswer v WHERE v.status = :status")})
public class VotingAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vote_id")
    private Long voteId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id")
    @ManyToOne
    private Answers answerId;

    public VotingAnswer() {
    }

    public VotingAnswer(Long voteId) {
        this.voteId = voteId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Answers getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answers answerId) {
        this.answerId = answerId;
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
        if (!(object instanceof VotingAnswer)) {
            return false;
        }
        VotingAnswer other = (VotingAnswer) object;
        if ((this.voteId == null && other.voteId != null) || (this.voteId != null && !this.voteId.equals(other.voteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.VotingAnswer[ voteId=" + voteId + " ]";
    }
    
}
