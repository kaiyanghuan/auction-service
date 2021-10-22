//package com.ocbc.auctionservice.configurations;
//
//import feign.Client;
//import feign.Request;
//import feign.Response;
//import org.springframework.util.StreamUtils;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLSocketFactory;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.Map;
//
//public class CustomFeignClient extends Client.Default{
//
//    public CustomFeignClient(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
//        super(sslContextFactory, hostnameVerifier);
//    }
//
//    @Override
//    public Response execute(Request request, Request.Options options) throws IOException {
//        Response response = super.execute(request, options);
//        Map<String, Collection<String>> headers = response.headers();
//        if(response.body() != null){
//            InputStream bodyStream = response.body().asInputStream();
//            String responseBody = StreamUtils.copyToString(bodyStream, StandardCharsets.UTF_8);
//            return response.toBuilder()
//                    .body(responseBody, StandardCharsets.UTF_8)
//                    .headers(headers)
//                    .build();
//        }
//        return response;
//    }
//}
