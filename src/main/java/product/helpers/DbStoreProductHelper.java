package product.helpers;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;
import product.models.Category;
import product.models.Product;
import product.repository.CategoryRepository;
import product.repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
public class DbStoreProductHelper {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ExecutorService executorService;

    private final FakeStoreProductHelper fakeStoreProductHelper;

    @Autowired
    DbStoreProductHelper(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, ExecutorService executorService, FakeStoreProductHelper fakeStoreProductHelper) {
        this.productRepository = Objects.requireNonNull(productRepository);
        this.modelMapper = Objects.requireNonNull(modelMapper);
        this.categoryRepository = Objects.requireNonNull(categoryRepository);
        this.executorService = Objects.requireNonNull(executorService);
        this.fakeStoreProductHelper = fakeStoreProductHelper;
    }

    public List<ProductDto> getAllProducts() throws Exception {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            return products.stream()
                    .map(product -> modelMapper.map(product, ProductDto.class))
                    .collect(Collectors.toList());
        } else {
            List<ProductDto> productDto = fakeStoreProductHelper.getAllProducts();
            saveAllProductsAsync(productDto);
            return productDto;
        }
    }

    public ProductDto getProductById(Long id) throws ProductNotFoundException, ResponseBodyNullException, IOException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            System.out.println("product was present inside DB ");
            return modelMapper.map(product, ProductDto.class);
        }
        ProductDto productDto = fakeStoreProductHelper.getProductById(id);
        saveProductAsync(productDto);
        return productDto;
    }

    @Transactional
    public ProductDto addProduct(ProductDto productDto) {
        if (productDto.getCategory() != null) {
            Optional<Category> category = categoryRepository.findById(productDto.getCategory().getCategoryId());
            if (category.isEmpty()) {
                Category newCategory = productDto.getCategory();
                System.out.println("saving category inside DB");
                categoryRepository.save(newCategory);
            }
        }
        Product product = modelMapper.map(productDto, Product.class);
        System.out.println("saving product inside DB");
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }


    private void saveProductAsync(ProductDto productDto) {
        System.out.println("saving product Async --> ");
        CompletableFuture.runAsync(() -> {
            addProduct(productDto);
        }, executorService).exceptionally(ex -> {
            ex.printStackTrace(); //
            return null;
        });
    }

    private void saveAllProductsAsync(List<ProductDto> productDtos) {
        System.out.println("Saving all products asynchronously...");
        CompletableFuture.runAsync(() -> {
            for (ProductDto productDto : productDtos) {
                addProduct(productDto);
            }
            ;
        }, executorService).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }
}
