package fi.haagahelia.blogmanagment.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	
	// Username with unique constraint
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	private String role;
		
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private List<Article> ownArticles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
	private List<Comment> ownComments;

	
	//constructor
	public Member(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Member() {};


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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Article> getOwnArticles() {
		return ownArticles;
	}


	public void setOwnArticles(List<Article> ownArticles) {
		this.ownArticles = ownArticles;
	}


	public List<Comment> getOwnComments() {
		return ownComments;
	}


	public void setOwnComments(List<Comment> ownComments) {
		this.ownComments = ownComments;
	}
	
	//getters/setters
	

}
