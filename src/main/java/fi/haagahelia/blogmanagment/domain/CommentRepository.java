package fi.haagahelia.blogmanagment.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long>{

	List<Comment> findByArticle(Article article);
}
