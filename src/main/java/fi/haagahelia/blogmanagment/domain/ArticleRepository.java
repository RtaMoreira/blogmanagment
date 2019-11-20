package fi.haagahelia.blogmanagment.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface ArticleRepository extends CrudRepository<Article,Long>{
	
    List<Article> findByTitle(String title);

    List<Article> findByCategory(Category category);
    
    List<Article> findByAuthor(Member author);
    

}
