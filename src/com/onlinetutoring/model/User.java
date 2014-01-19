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
  * User.java		
  * @author Harshitha de Silva
  * 
  * @since Jan 2, 2014 1:55:22 AM
  */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByEmailAddress", query = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByCreatedDate", query = "SELECT u FROM User u WHERE u.createdDate = :createdDate"),
    @NamedQuery(name = "User.findByIsBanded", query = "SELECT u FROM User u WHERE u.isBanded = :isBanded"),
    @NamedQuery(name = "User.findByReputation", query = "SELECT u FROM User u WHERE u.reputation = :reputation"),
    @NamedQuery(name = "User.findByLoyality", query = "SELECT u FROM User u WHERE u.loyality = :loyality")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Long userId;
    @Size(max = 45)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 45)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 256)
    @Column(name = "password")
    private String password;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_banded")
    private int isBanded;
    @Column(name = "is_pending")
    private int is_pending;
    @Column(name = "reputation")
    private Long reputation;
    @Column(name = "loyality")
    private Long loyality; 
    @JoinColumn(name = "roleId", referencedColumnName = "role_id")
    @ManyToOne
    private UserRole roleId;

    public User() {
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public User(Long userId, int isBanded) {
        this.userId = userId;
        this.isBanded = isBanded;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getIsBanded() {
        return isBanded;
    }

    public void setIsBanded(int isBanded) {
        this.isBanded = isBanded;
    }

    public Long getReputation() {
        return reputation;
    }

    public void setReputation(Long reputation) {
        this.reputation = reputation;
    }

    public Long getLoyality() {
        return loyality;
    }

    public void setLoyality(Long loyality) {
        this.loyality = loyality;
    }

    public UserRole getRoleId() {
        return roleId;
    }

    public void setRoleId(UserRole roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.onlinetutoring.model.User[ userId=" + userId + " ]";
    }

	public int getIs_pending() {
		return is_pending;
	}

	public void setIs_pending(int is_pending) {
		this.is_pending = is_pending;
	}
    
}
