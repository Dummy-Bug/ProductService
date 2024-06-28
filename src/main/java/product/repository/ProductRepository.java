package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import product.models.Product;
import product.projection.ProductWithTitleAndDescription;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    //HQL
    @Query("select p.title as title, p.description as description from product p where p.id = :id")
    ProductWithTitleAndDescription fetchTitleAndDescription(@Param("id") Long id);
}
