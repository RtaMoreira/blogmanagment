package fi.haagahelia.blogmanagment.domain;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.haagahelia.blogmanagment.BlogmanagmentApplication;

@Entity
public class Article {
	private static final Logger log = LoggerFactory.getLogger(BlogmanagmentApplication.class);

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	private Date date;
	
    @Column(name="text", columnDefinition="CLOB NOT NULL") 
	private String text;
    @Column(name="imageURL", columnDefinition="CLOB NOT NULL") 
	private String imageURL;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name="art_author")
	private Member author;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name="art_category")
	private Category category;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
	private List<Comment> comments;
	
	//constructor
	public Article(String title, Date date, String text, String imageURL, Member author, Category category) {
		super();
		this.title = title;
		this.date = date;
		this.text = text;
		this.imageURL = imageURL;
		this.author = author;
		this.category = category;
	}	
	
	public Article() {};
	
	
	
	public Article(Member author) {
		super();
		this.author = author;
	}

	public void saveMultipartFile( MultipartFile files)
	{
	    if(!files.isEmpty()){
	        try {
	            String fileName = files.getOriginalFilename();
	            String dirLocation ="/app/src/main/resources/static/img/";
	                File file = new File(dirLocation);
	                file.mkdirs();
		            System.out.print("PATH :"+System.getProperty("user.dir"));
		            System.out.print("FILE PATH :"+file.getAbsolutePath());
		            log.info("PATH OF STORED IMAGE :"+file.getAbsolutePath());

	            byte[] bytes = files.getBytes();
	            File newFile = new File(fileName);
	            System.out.println("DIRLOCATION + FILENAME :"+dirLocation+newFile);
	            System.out.println("NEW FILE LOCATION : "+newFile.getAbsolutePath());

	            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dirLocation+newFile));
	            bufferedOutputStream.write(bytes);
	            bufferedOutputStream.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            log.info("ERROR saving file");
	            log.info("position  :"+System.getProperty("user.dir"));
	        }
	    }
	    
	}

	//getters/setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public Member getAuthor() {
		return author;
	}


	public void setAuthor(Member author) {
		this.author = author;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

}
