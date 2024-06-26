package product.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @NotNull
    @Override
    Optional<Product> findById(@NotNull Long id);
}
