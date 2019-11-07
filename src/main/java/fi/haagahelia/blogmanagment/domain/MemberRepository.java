package fi.haagahelia.blogmanagment.domain;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long>{

	Member findByUsername(String username);
}
