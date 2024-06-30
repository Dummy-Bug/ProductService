package product.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import product.dtos.UserDto;


@Component
public class Auth {

    private final OkHttpClient okHttpClient;

    private final ObjectMapper mapper;

    private static final String VALIDATE_TOKEN_URL = "http://127.0.0.1:8091/users/validate/%s";

    Auth(OkHttpClient okHttpClient, ObjectMapper mapper) {
        this.okHttpClient = okHttpClient;
        this.mapper = mapper;
    }

    public UserDto validateToken(String token) throws Exception {
        Request request = new Request.Builder()
                .url(String.format(VALIDATE_TOKEN_URL, token))
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String responseBody = response.body().string();

        if (responseBody.toLowerCase().contains("invalid token")) {
            throw new Exception("Invalid Token");
        }
        return mapper.readValue(responseBody, UserDto.class);
    }
}
