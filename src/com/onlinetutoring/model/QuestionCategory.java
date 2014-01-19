 package com.onlinetutoring.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

 /**
 * 
 * QuestionCategory.java		
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:55:06 AM
 */ 
@Entity
@Table(name = "question_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionCategory.findAll", query = "SELECT q FROM QuestionCategory q"),
    @NamedQuery(name = "QuestionCategory.findByQuestionCategoryId", query = "SELECT q FROM QuestionCategory q WHERE q.questionCategoryId = :questionCategoryId"),
    @NamedQuery(name = "QuestionCategory.findByQuestionCategory", query = "SELECT q FROM QuestionCategory q WHERE q.questionCategory = :questionCategory")})
public class QuestionCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "question_category_id")
    private Long questionCategoryId;
    @Size(max = 45)
    @Column(name = "question_category")
    private String questionCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Questions> questionsCollection;

    public QuestionCategory() {
    }

    public QuestionCategory(Long questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public Long getQuestionCategoryId() {
        return questionCategoryId;
    }

    public void setQuestionCategoryId(Long questionCategoryId) {
        this.questionCategoryId = questionCategoryId;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    @XmlTransient
    public Collection<Questions> getQuestionsCollection() {
        return questionsCollection;
    }

    public void setQuestionsCollection(Collection<Questions> questionsCollection) {
        this.questionsCollection = questionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionCategoryId != null ? questionCategoryId.hashCode() : 0);
        return hash;
    }

  /*  @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionCategory)) {
            return false;
        }
        QuestionCategory other = (QuestionCategory) object;
        if ((this.questionCategoryId == null && other.questionCategoryId != null) || (this.questionCategoryId != null && !this.questionCategoryId.equals(other.questionCategoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.QuestionCategory[ questionCategoryId=" + questionCategoryId + " ]";
    }
    */
}
 