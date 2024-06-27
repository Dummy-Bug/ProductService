package product.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;
import product.models.Product;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
public class FakeStoreProductHelper {

    private static final String BASE_URL = "https://api.escuelajs.co/api/v1";
    private static final String GET_ALL_PRODUCTS_URL = "/products";
    private static final String GET_SINGLE_PRODUCT_URL = "/products/%s";
    private static final String FILTER_PRODUCT_BY_TITLE_URL = "/products/?title=%s";
    private static final String JOIN_FILTER_PRODUCT_URL = "/products/?price_min=%s&price_max=%s&offset=10&limit=%s";
    private static final String PAGINATION_PRODUCTS_URL = "/products/?offset=0&limit=10";
    private static final String GET_ALL_CATEGORIES_URL = "/categories";
    private static final String GET_SINGLE_CATEGORY_URL = "categories/%s";
    private static final String GET_ALL_PRODUCTS_FROM_CATEGORY_URL = "/categories/1/products";
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final DbStoreProductHelper dbStoreProductHelper;
    private final ModelMapper modelMapper;
    private final ExecutorService executorService;

    @Autowired
    public FakeStoreProductHelper(OkHttpClient okHttpClient, ObjectMapper objectMapper, DbStoreProductHelper dbStoreProductHelper, ModelMapper modelMapper) {
        this.okHttpClient = Objects.requireNonNull(okHttpClient);
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.dbStoreProductHelper = Objects.requireNonNull(dbStoreProductHelper);
        this.modelMapper = Objects.requireNonNull(modelMapper);
        this.executorService = Objects.requireNonNull(Executors.newCachedThreadPool());
    }

    public List<ProductDto> getAllProducts() throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_ALL_PRODUCTS_URL)
                .build();
        System.out.println("API call --> " + request.url());
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = getResponseBodyFromResponse(response);
        List<Product> products = objectMapper.readValue(responseBody, new TypeReference<List<Product>>() {
        });
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        saveProductsAsync(products);

        return productDtos;
    }

    private String getResponseBodyFromResponse(Response response) throws Exception {
        try (ResponseBody responseBody = response.body()) {
            if (responseBody != null) {
                return responseBody.string();
            }
            throw new Exception("Response Body is Null");
        }
    }

    public ProductDto getProductById(Long id) throws Exception {

        Request request = new Request.Builder()
                .url(String.format(BASE_URL + GET_SINGLE_PRODUCT_URL, id))
                .build();
        System.out.println("API call --> " + request.url());
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = getResponseBodyFromResponse(response);
        saveProductAsync(objectMapper.readValue(responseBody, Product.class));
        return objectMapper.readValue(responseBody, ProductDto.class);
    }

    private void saveProductAsync(Product product) {
        System.out.println("saving product Async --> ");
        CompletableFuture.runAsync(() -> {
            dbStoreProductHelper.addProduct(product);
        }, executorService).exceptionally(ex -> {
            ex.printStackTrace(); //
            return null;
        });
    }

    private void saveProductsAsync(List<Product> products) {
        System.out.println("saving list of products Async --> ");
        CompletableFuture.runAsync(() -> {
            products.forEach(dbStoreProductHelper::addProduct);
        }, executorService).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }
}
