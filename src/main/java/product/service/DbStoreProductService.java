package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;
import product.helpers.DbStoreProductHelper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service("dbStoreProductService")
//@Primary
public class DbStoreProductService implements ProductService {
    DbStoreProductHelper DbStoreProductHelper;

    @Autowired
    public DbStoreProductService(DbStoreProductHelper DbStoreProductHelper) {
        this.DbStoreProductHelper = Objects.requireNonNull(DbStoreProductHelper);
    }

    public List<ProductDto> getAllProducts() throws Exception {
        return DbStoreProductHelper.getAllProducts();
    }

    @Override
    public ProductDto getProductById(long id) throws ProductNotFoundException, ResponseBodyNullException, IOException {
        return DbStoreProductHelper.getProductById(id);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return DbStoreProductHelper.addProduct(productDto);
    }

    @Override
    public ProductDto replaceProduct(long id, ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        return null;
    }

    @Override
    public void removeProduct(long id) {
    }
}
