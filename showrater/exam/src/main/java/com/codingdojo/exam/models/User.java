package com.codingdojo.exam.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.mindrot.jbcrypt.BCrypt;

import com.codingdojo.exam.models.Show;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="show_id")
    private Show show;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Rating> ratings;
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	public User () {
	}
	
	public User (Map<String, String> body) {
		this.username = body.get("username");
		this.email = body.get("email");
		this.password = BCrypt.hashpw(body.get("password"), BCrypt.gensalt());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
