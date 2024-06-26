package product.helpers;

import org.springframework.stereotype.Component;
import product.dtos.ProductDto;

import java.util.List;

@Component
public class DBStoreProductHelper {
    public List<ProductDto> getAllProducts() {
        System.out.println("Reached Helper");
        return null;
    }
    public ProductDto getProductById(Long id) throws Exception {
        return null;
    }
}
