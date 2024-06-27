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
import product.exceptions.ProductNotFoundException;
import product.exceptions.ResponseBodyNullException;
import product.models.Product;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
    private final ModelMapper modelMapper;


    @Autowired
    public FakeStoreProductHelper(OkHttpClient okHttpClient, ObjectMapper objectMapper, ModelMapper modelMapper) {
        this.okHttpClient = Objects.requireNonNull(okHttpClient);
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.modelMapper = Objects.requireNonNull(modelMapper);
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
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    private String getResponseBodyFromResponse(Response response) throws ResponseBodyNullException {
        try (ResponseBody responseBody = response.body()) {
            if (responseBody != null) {
                return responseBody.string();
            }
            throw new ResponseBodyNullException("Response Body is Null");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductDto getProductById(Long id) throws ProductNotFoundException, ResponseBodyNullException, IOException {

        Request request = new Request.Builder()
                .url(String.format(BASE_URL + GET_SINGLE_PRODUCT_URL, id))
                .build();
        System.out.println("API call --> " + request.url());
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = getResponseBodyFromResponse(response);
        if (response.code() == 400) {
            throw new ProductNotFoundException("No product is present with id = " + id);
        }
        return objectMapper.readValue(responseBody, ProductDto.class);
    }
}
