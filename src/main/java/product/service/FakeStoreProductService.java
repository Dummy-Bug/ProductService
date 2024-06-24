package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.helpers.FakeStoreProductHelper;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    FakeStoreProductHelper fakeStoreProductHelper;

    @Autowired
    public FakeStoreProductService(FakeStoreProductHelper fakeStoreProductHelper) {
        this.fakeStoreProductHelper = fakeStoreProductHelper;
    }

    public List<ProductDto> getAllProducts() {
        return fakeStoreProductHelper.getAllProducts();
    }

    @Override
    public ProductDto getProductById(long id) throws Exception {
        return fakeStoreProductHelper.getProductById(id);
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
