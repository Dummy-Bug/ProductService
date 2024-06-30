package product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import product.commons.Auth;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.dtos.UserDto;
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;
    private final Auth auth;

    @Autowired
    public ProductController(@Qualifier("dbStoreProductService") ProductService productService, Auth auth) {
        this.productService = Objects.requireNonNull(productService);
        this.auth = auth;
    }

    @GetMapping(produces = "application/json")
    public List<ProductDto> getAllProducts() throws Exception {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id, @RequestHeader("authToken") String token) throws Exception {
        UserDto userDto = auth.validateToken(token);
        if (userDto == null) {
            throw new Exception("Invalid token from User Service");
        }
        System.out.println("Email --> " + userDto.getEmail() +
                "\t Name" + userDto.getName());
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
