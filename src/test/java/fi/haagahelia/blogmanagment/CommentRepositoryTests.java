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
import fi.haagahelia.blogmanagment.domain.Comment;
import fi.haagahelia.blogmanagment.domain.CommentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTests {
	
	@Autowired
	private CommentRepository comRepo;
	
	@Autowired
	private ArticleRepository artRepo;


	@Test
	public void findByArticleShouldReturnComments() {
		List<Comment> comments = comRepo.findByArticle(artRepo.findByTitle("Cat credited with saving baby from falling down the stairs").get(0));
        assertThat(comments).isNotEmpty();
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
		List<Comment> commentsBefore = comRepo.findByArticle(artRepo.findByTitle("Cat credited with saving baby from falling down the stairs").get(0));
		comRepo.delete(commentsBefore.get(0));
		List<Comment> commentsAfter  = comRepo.findByArticle(artRepo.findByTitle("Cat credited with saving baby from falling down the stairs").get(0));
    	assertThat(commentsAfter.size()<commentsBefore.size());
    }
	
}
