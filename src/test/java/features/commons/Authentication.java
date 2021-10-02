package features.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.auctionservice.controllers.requests.LoginRequest;
import features.constants.Constant;
import features.context.Context;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Authentication {

    private static final String authUrl = "/auth/login";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        try {
            RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), objectMapper.writeValueAsString(loginRequest));
            Response response = CommonApis.login(Constant.baseUrl + authUrl, requestBody);
            if (response.isSuccessful() && response.body() != null) {
                AuthenticationResponse authenticationResponse = objectMapper.readValue(response.body().string(), AuthenticationResponse.class);
                Context.user = new TestUser(true, authenticationResponse.accessToken, username);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
