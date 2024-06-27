package product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("dbStoreProductService") ProductService productService) {
        this.productService = Objects.requireNonNull(productService);
    }

    @GetMapping(produces = "application/json")
    public List<ProductDto> getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id) throws ProductNotFoundException, ResponseBodyNullException, IOException {
        try {
            return productService.getProductById(id);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @PostMapping(consumes = "application/json")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ProductDto replaceProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        return productService.replaceProduct(id, productDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
    public ProductDto updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping(value = "/{id}", params = "confirm=true")
    public void removeProduct(@PathVariable long id) {
        productService.removeProduct(id);
    }
}
