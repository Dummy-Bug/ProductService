package product.commons;

import product.dtos.ProductDto;

import java.util.List;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts() throws Exception;

    ProductDto getProductById(long id) throws Exception;

    ProductDto addProduct(ProductDto productDto);

    ProductDto replaceProduct(long id, ProductDto productDto);

    ProductDto updateProduct(long id, ProductDto productDto);

    void removeProduct(long id);
}

