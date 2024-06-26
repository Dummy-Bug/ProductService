package product.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    public FakeStoreProductHelper(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.okHttpClient = Objects.requireNonNull(okHttpClient);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }


    public List<ProductDto> getAllProducts() {
        System.out.println("Reached Helper");
        return null;
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
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = getResponseBodyFromResponse(response);
        return objectMapper.readValue(responseBody, ProductDto.class);
    }
}
