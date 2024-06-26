package product.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;
import product.models.Product;
import product.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class DbStoreProductHelper {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Autowired
    DbStoreProductHelper(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.modelMapper = Objects.requireNonNull(modelMapper);
    }

    public List<ProductDto> getAllProducts() {
        System.out.println("Reached Helper");
        return null;
    }

    public ProductDto getProductById(Long id) throws Exception {
        return modelMapper.map(productRepository.findById(id), ProductDto.class);
    }
}
