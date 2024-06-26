package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
