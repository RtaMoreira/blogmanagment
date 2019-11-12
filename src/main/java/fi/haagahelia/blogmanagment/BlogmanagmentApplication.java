package fi.haagahelia.blogmanagment;


import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.blogmanagment.domain.Article;
import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Category;
import fi.haagahelia.blogmanagment.domain.CategoryRepository;
import fi.haagahelia.blogmanagment.domain.Comment;
import fi.haagahelia.blogmanagment.domain.CommentRepository;
import fi.haagahelia.blogmanagment.domain.Member;
import fi.haagahelia.blogmanagment.domain.MemberRepository;


@SpringBootApplication
public class BlogmanagmentApplication {
	private static final Logger log = LoggerFactory.getLogger(BlogmanagmentApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BlogmanagmentApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(ArticleRepository artRepo, CategoryRepository catRepo,MemberRepository memRepo, CommentRepository comRepo) {
	
		return (args) -> {
            
			catRepo.save(new Category("Business"));
			catRepo.save(new Category("Sports"));
			catRepo.save(new Category("Lifestyle"));
			catRepo.save(new Category("Health"));
			

			Member mem1 = new Member("user", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2","USER"); //pwd : user
			Member mem2 = new Member("bob", "$2a$10$pcyvJXKC3KyOBx988OOBB.Zx6qTNCWJoUNWW7rAJQPpOWPz0mo/n2","USER"); //pwd : user
			
			memRepo.save(mem1);
			memRepo.save(mem2);
			artRepo.save(new Article("Zidane: Mbappe dreams of Real Madrid move", new Date(), "Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc. Sed vehicula egestas faucibus. Ut tristique leo metus, eget pharetra libero sodales quis. Aliquam maximus volutpat efficitur. Ut bibendum imperdiet felis tincidunt accumsan.", "sports.jpg", mem1, catRepo.findByName("Sports")));
			artRepo.save(new Article("Cat credited with saving baby from falling down the stairs", new Date(), "Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc. Sed vehicula egestas faucibus. Ut tristique leo metus, eget pharetra libero sodales quis. Aliquam maximus volutpat efficitur. Ut bibendum imperdiet felis tincidunt accumsan.", "lifestyle.jpg", mem1, catRepo.findByName("Lifestyle")));
			
			artRepo.save(new Article("WeWork says it will divest all ‘non-core’ businesses", new Date(), "Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nuncInteger nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc.Integer nec posuere elit, eu fermentum nunc. Sed vehicula egestas faucibus. Ut tristique leo metus, eget pharetra libero sodales quis. Aliquam maximus volutpat efficitur. Ut bibendum imperdiet felis tincidunt accumsan.", "business.jpeg", mem2, catRepo.findByName("Business")));
			artRepo.save(new Article("This vitamin D mechanism helps combat melanoma", new Date(), "Integer nec posuere elit, eu fermentum nunc. Sed vehicula egestas faucibus. Ut tristique leo metus, eget pharetra libero sodales quis. Aliquam maximus volutpat efficitur. Ut bibendum imperdiet felis tincidunt accumsan.", "health.jpg", mem2, catRepo.findByName("Health")));

			for (Article article : artRepo.findAll()) {
				log.info(article.toString());
			}
			
			//Comment com1 = new Comment( memRepo.findByUsername("bob"), artRepo.findByTitle("Article 1 title").get(0),(new Date()), "blablabla im commenting you post", "such a good comment");
			comRepo.save(new Comment("Integer nec posuere elit.eu fermentum nuncInteger nec posuere elit, eu fermentum nunc. Integer nec posuere elit, eu fermentum nuncInteger.", "How can he? He is betraying France!!",new Date(), memRepo.findByUsername("bob"), artRepo.findByTitle("Zidane: Mbappe dreams of Real Madrid move").get(0)));
			comRepo.save(new Comment("Integer nec posuere elit.eu fermentum nunc.", "What about Juventus?",new Date(), memRepo.findByUsername("bob"), artRepo.findByTitle("Zidane: Mbappe dreams of Real Madrid move").get(0)));
			comRepo.save(new Comment("Integer nec posuere elit.", "I love cats !!",new Date(), memRepo.findByUsername("user"), artRepo.findByTitle("Cat credited with saving baby from falling down the stairs").get(0)));
			comRepo.save(new Comment("nteger nec posuere elit. eu fermentum nunc.", "Who run the world? CATS! ",new Date(), memRepo.findByUsername("user"), artRepo.findByTitle("Cat credited with saving baby from falling down the stairs").get(0)));
			

		};
	}
	
}
