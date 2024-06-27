package product.commons;

import product.dtos.ProductDto;
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;

import java.io.IOException;
import java.util.List;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts() throws Exception;

    ProductDto getProductById(long id) throws ProductNotFoundException, ResponseBodyNullException, IOException;

    ProductDto addProduct(ProductDto productDto);

    ProductDto replaceProduct(long id, ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    void removeProduct(long id);
}

