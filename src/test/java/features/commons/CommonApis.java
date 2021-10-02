package features.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import features.configurations.SSLConfig;
import features.context.Context;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class CommonApis {

    private static final OkHttpClient okHttpClient = SSLConfig.trustAllSslClient(new OkHttpClient());
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void get(String url, String jwtToken) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + jwtToken)
                .addHeader("Content-Type", "application/json")
                .url(url)
                .get()
                .build();
        Context.response = okHttpClient.newCall(request).execute();
    }

    public static void post(String url, String jwtToken, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + jwtToken)
                .addHeader("Content-Type", "application/json")
                .url(url)
                .post(requestBody)
                .build();
        Context.response = okHttpClient.newCall(request).execute();
    }

    public static void put(String url, String jwtToken, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + jwtToken)
                .addHeader("Content-Type", "application/json")
                .url(url)
                .put(requestBody)
                .build();
        Context.response = okHttpClient.newCall(request).execute();
    }

    public static void delete(String url, String jwtToken) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + jwtToken)
                .addHeader("Content-Type", "application/json")
                .url(url)
                .delete()
                .build();
        Context.response = okHttpClient.newCall(request).execute();
    }

    public static Response login(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return okHttpClient.newCall(request).execute();
    }
}
