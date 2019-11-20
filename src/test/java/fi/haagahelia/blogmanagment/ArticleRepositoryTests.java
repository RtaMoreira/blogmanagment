package fi.haagahelia.blogmanagment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.blogmanagment.domain.Article;
import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.CategoryRepository;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryTests {

	@Autowired
	private ArticleRepository repository;
	@Autowired
	private MemberRepository memRepository;
	@Autowired
	private CategoryRepository catRepository;
	
	@Test
	public void findByTitleShouldReturnArticle() {
		List<Article> article = repository.findByTitle("title of article");
		
        assertThat(article).hasSize(1);
        assertThat(article.get(0).getId()).isNotNull();
	}
	
	@Test
	public void createNewArticle() {
		Article article = new Article("title", new Date(), "text of the article", "business.jpeg", memRepository.findByUsername("user"), catRepository.findByName("Business"));
		repository.save(article);
		assertThat(article.getId()).isNotNull();
	}
	
    @Test
    public void deleteArticle() {
        List<Article> articles = repository.findByTitle("title of article");
        repository.delete(articles.get(0));
        articles = repository.findByTitle("title of article");
    	assertThat(articles.isEmpty());
    }
}
