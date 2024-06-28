package product.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import product.projection.ProductWithTitleAndDescription;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findById() {
        System.out.println(productRepository.findById(3L));
    }
    @Test
    void fetchTitleAndDescription() {
        Long productId = 3L;

        ProductWithTitleAndDescription result = productRepository.fetchTitleAndDescription(productId);
        System.out.println(result.toCustomString());
    }
}