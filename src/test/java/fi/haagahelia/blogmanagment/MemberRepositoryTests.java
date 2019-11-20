package fi.haagahelia.blogmanagment;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import fi.haagahelia.blogmanagment.domain.Member;
import fi.haagahelia.blogmanagment.domain.MemberRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTests {
	
	@Autowired
	private MemberRepository memRepository;
	
	@Test
	public void findByUsernameShouldReturnMember() {
		Member member = memRepository.findByUsername("user");
		
        assertThat(member).isNotNull();
        assertThat(member.getId()).isNotNull();
	}

	
}
