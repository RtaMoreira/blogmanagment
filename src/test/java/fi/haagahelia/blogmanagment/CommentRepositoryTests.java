package fi.haagahelia.blogmanagment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.blogmanagment.domain.Article;
import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Comment;
import fi.haagahelia.blogmanagment.domain.CommentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTests {
	
	private CommentRepository comRepo;
	private ArticleRepository artRepo;


	@Test
	public void findByArticleShouldReturnComments() {
		List<Comment> comments = comRepo.findByArticle(artRepo.findByTitle("title of article").get(0));
        assertThat(comments).hasSize(1);
        assertThat(comments.get(0).getId()).isNotNull();
	}
	
	@Test
	public void createNewComment() {
		Comment comment = new Comment();
		comRepo.save(comment);
		assertThat(comment.getId()).isNotNull();
	}
	
    @Test
    public void deleteComment() {
		List<Comment> comments = comRepo.findByArticle(artRepo.findByTitle("title of article").get(0));
		comRepo.delete(comments.get(0));
		comments = comRepo.findByArticle(artRepo.findByTitle("title of article").get(0));
    	assertThat(comments.isEmpty());
    }
	
}
