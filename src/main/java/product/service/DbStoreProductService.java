package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.helpers.DbStoreProductHelper;
import product.helpers.FakeStoreProductHelper;

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

    public List<ProductDto> getAllProducts() {
        return DbStoreProductHelper.getAllProducts();
    }

    @Override
    public ProductDto getProductById(long id) throws Exception {
        return DbStoreProductHelper.getProductById(id);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return null;
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
