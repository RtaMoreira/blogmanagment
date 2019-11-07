package fi.haagahelia.blogmanagment.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String text;
	private String title;
	private Date date;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name="com_author")
	private Member author;

	@ManyToOne
    @JsonIgnore
	@JoinColumn(name="com_art")
	private Article article;
	
	
	//constructor	
	public Comment() {};
	
	

	public Comment(Member author, Article article) {
		super();
		this.author = author;
		this.article = article;
	}



	public Comment(String text, String title, Date date, Member author, Article article) {
		super();
		this.text = text;
		this.title = title;
		this.date = date;
		this.author = author;
		this.article = article;
	}


	//getters/setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Member getAuthor() {
		return author;
	}


	public void setAuthor(Member author) {
		this.author = author;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}

	
	
	

}
