package product.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import product.dtos.ProductDto;

import java.util.List;

@Component
public class FakeStoreProductHelper {

    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    public FakeStoreProductHelper(OkHttpClient okHttpClient, ObjectMapper objectMapper) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = objectMapper;
    }


    public List<ProductDto> getAllProducts() {
        System.out.println("Reached Helper");
        return null;
    }

    public ProductDto getProductById(Long id) throws Exception {

        Request request = new Request.Builder()
                .url(String.format("https://fakestoreapi.com/products/%s", id))
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return objectMapper.readValue(response.body().string(), ProductDto.class);
    }
}
