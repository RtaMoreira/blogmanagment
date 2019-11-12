package fi.haagahelia.blogmanagment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.blogmanagment.web.ArticleController;
import fi.haagahelia.blogmanagment.web.MemberController;

/**
 * Smoked testing for all controllers
 * @author Rita
 */
@SpringBootTest
class BlogmanagmentApplicationTests {

	@Autowired
	private ArticleController artController;
	
	@Autowired
	private MemberController memController;
	
	@Test
	public void contextLoads() throws Exception {
	assertThat(artController).isNotNull();
	assertThat(memController).isNotNull();

	}

}
