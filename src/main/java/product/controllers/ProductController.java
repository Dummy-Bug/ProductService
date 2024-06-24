package product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.commons.ProductService;
import product.dtos.ProductDto;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = "application/json")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id) throws Exception {
        return productService.getProductById(id);
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
