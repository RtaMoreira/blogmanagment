package fi.haagahelia.blogmanagment.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {

	Category findByName(String name);

	
}
