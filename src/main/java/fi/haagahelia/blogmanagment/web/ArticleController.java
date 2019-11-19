package fi.haagahelia.blogmanagment.web;


import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import fi.haagahelia.blogmanagment.BlogmanagmentApplication;
import fi.haagahelia.blogmanagment.domain.Article;
import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Category;
import fi.haagahelia.blogmanagment.domain.CategoryRepository;
import fi.haagahelia.blogmanagment.domain.Comment;
import fi.haagahelia.blogmanagment.domain.CommentRepository;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@Controller
public class ArticleController {
	private static final Logger log = LoggerFactory.getLogger(BlogmanagmentApplication.class);
	private static Authentication auth;

	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "src/main/resources/static/img/";

	@Autowired
	private ArticleRepository repository;

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private CommentRepository comRepo;

	@Autowired
	private MemberRepository memRepo;

	// show all articles
	@RequestMapping(value = "/articles")
	public String articlesList(Model model) {
		model.addAttribute("articles", repository.findAll());
		return "articlesList";
	}

	// show all articles by category
	@RequestMapping(value = "/articles/{category}")
	public String articlesListbyCategory(@PathVariable String category, Model model) {
		Category cat = catRepo.findByName(category);
		List<Article> articles = repository.findByCategory(cat);
		model.addAttribute("articles", articles);
		return "articlesList";
	}

	// show one article (detail)
	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public String articleDetail(@PathVariable("id") Long articleId, Model model) {

		auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username
		
		if (loggedUser.isEmpty()) {
			Article art = repository.findById(articleId).get();
				byte[] decodedBytes = Base64.getDecoder().decode(art.getImageURL());
				//try {
					//File image = new File("image.jpg");
					//System.out.println("IMAGE PAAAAAATH : "+ image.getAbsolutePath());
					//FileUtils.writeByteArrayToFile(image, decodedBytes);
					//ModelMap map = new ModelMap();
					model.addAttribute("image", decodedBytes);
				//} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//System.out.println("ERROOR : "+e.getMessage());
				//}
				model.addAttribute("article", art);


				// get comments of that article
				model.addAttribute("comments", comRepo.findByArticle(art));

				// instantiate a comment model (for adding comment)
				model.addAttribute("comment", new Comment());
		} else {

			// unwrap the object "article" from Optional<Article>
			repository.findById(articleId).ifPresent(art -> {
				
				byte[] decodedBytes = Base64.getDecoder().decode(art.getImageURL());
					model.addAttribute("image", decodedBytes);
					
				model.addAttribute("article", art);

				// get comments of that article
				model.addAttribute("comments", comRepo.findByArticle(art));

				// instantiate a comment model (for adding comment)
				model.addAttribute("comment", new Comment(memRepo.findByUsername(loggedUser), art));
			});
		}

		return "articleDetail";
	}

	// edit one article
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String articleEdit(@PathVariable("id") Long articleId, Model model) {
		model.addAttribute("article", repository.findById(articleId));
		model.addAttribute("categories", catRepo.findAll());

		return "editArticle";
	}

	// save article with image
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Article article, @RequestParam("file") MultipartFile file) {
		// formate date
		article.setDate(new Date());

		// if a new image is picked
		if (!file.getOriginalFilename().isEmpty()) {
	
			try {
				byte[] content = file.getBytes();
				article.setImageURL(Base64.getEncoder().encodeToString(content));	
				System.out.print("ENCODED IMAGEEEE");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//save filename in DB 
			//article.setImageURL(file.getOriginalFilename());
			//article.saveMultipartFile(file);
		}
		repository.save(article);
		return "redirect:articles";
	}

	// delete article
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteArticle(@PathVariable("id") Long articleId, Model model) {
		repository.deleteById(articleId);
		return "redirect:../articles";
	}

	// save comment
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public String saveComment(Comment comment) {
		// format date
		comment.setDate(new Date());
		comRepo.save(comment);
		return "redirect:article/" + comment.getArticle().getId().toString();
	}

	// add new article
	@RequestMapping(value = "/newarticle")
	public String addBook(Model model) {
		
		// get logged in username
		auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); 
		
		model.addAttribute("article", new Article(memRepo.findByUsername(loggedUser)));
		model.addAttribute("categories", catRepo.findAll());
		return "addArticle";
	}

}
