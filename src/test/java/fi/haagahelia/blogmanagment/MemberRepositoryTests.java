package fi.haagahelia.blogmanagment;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.blogmanagment.domain.ArticleRepository;
import fi.haagahelia.blogmanagment.domain.Member;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTests {
	private ArticleRepository artRepository;
	private MemberRepository memRepository;
	
	@Test
	public void findByUsernameShouldReturnMember() {
		Member member = memRepository.findByUsername("ritinha");
		
        assertThat(member).isNotNull();
        assertThat(member.getId()).isNotNull();
	}

	
}
