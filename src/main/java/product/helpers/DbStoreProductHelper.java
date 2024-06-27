package product.helpers;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;
import product.models.Category;
import product.models.Product;
import product.repository.CategoryRepository;
import product.repository.ProductRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DbStoreProductHelper {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Autowired
    DbStoreProductHelper(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.modelMapper = Objects.requireNonNull(modelMapper);
        this.categoryRepository = Objects.requireNonNull(categoryRepository);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) throws Exception {
        return modelMapper.map(productRepository.findById(id), ProductDto.class);
    }

    @Transactional
    public ProductDto addProduct(ProductDto productdto) {
        Product product = modelMapper.map(productdto, Product.class);
        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    @Transactional
    public ProductDto addProduct(Product product) {

        if (product.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(product.getCategory().getCategoryId());
            if (category.isEmpty()) {
                System.out.println("Category not found in DB");
                Category newCategory = product.getCategory();
                System.out.println("Saving category from API inside DB");
                categoryRepository.save(newCategory);
            }
        }
        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }
}
