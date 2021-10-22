//package com.ocbc.auctionservice.proxies;
//
//import com.ocbc.auctionservice.configurations.FeignClientConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@FeignClient(name = "ms-hello-world-proxy", url="${hello.world.application.url}", configuration = FeignClientConfig.class)
//public interface HelloWorldProxy {
//
//    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String getHelloWorld();
//
//    @PostMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    String postHelloWorld();
//}
