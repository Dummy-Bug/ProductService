package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.commons.ProductService;
import product.dtos.ProductDto;
import product.helpers.DBStoreProductHelper;

import java.util.List;

@Service("dbStoreProductService")
//@Primary
public class DataBaseProductService implements ProductService{
    DBStoreProductHelper DBStoreProductHelper;

    @Autowired
    public DataBaseProductService(DBStoreProductHelper DBStoreProductHelper) {
        this.DBStoreProductHelper = DBStoreProductHelper;
    }

    public List<ProductDto> getAllProducts() {
        return DBStoreProductHelper.getAllProducts();
    }

    @Override
    public ProductDto getProductById(long id) throws Exception {
        return DBStoreProductHelper.getProductById(id);
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
