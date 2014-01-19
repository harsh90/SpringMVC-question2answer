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
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * ViewQuestion.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 11, 2014 10:23:02 PM
 */
@Entity
@Table(name = "view_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewQuestion.findAll", query = "SELECT v FROM ViewQuestion v"),
    @NamedQuery(name = "ViewQuestion.findByViewId", query = "SELECT v FROM ViewQuestion v WHERE v.viewId = :viewId"),
    @NamedQuery(name = "ViewQuestion.findByQuestionId", query = "SELECT v FROM ViewQuestion v WHERE v.questionId = :questionId")})
public class ViewQuestion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "view_id")
    private Long viewId;
    @Column(name = "question_id")
    private Long questionId;
    @JoinColumn(name = "userid", referencedColumnName = "user_id")
    @ManyToOne
    private User userid;

    public ViewQuestion() {
    }

    public ViewQuestion(Long viewId) {
        this.viewId = viewId;
    }

    public Long getViewId() {
        return viewId;
    }

    public void setViewId(Long viewId) {
        this.viewId = viewId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (viewId != null ? viewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViewQuestion)) {
            return false;
        }
        ViewQuestion other = (ViewQuestion) object;
        if ((this.viewId == null && other.viewId != null) || (this.viewId != null && !this.viewId.equals(other.viewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.ViewQuestion[ viewId=" + viewId + " ]";
    }
    
}
