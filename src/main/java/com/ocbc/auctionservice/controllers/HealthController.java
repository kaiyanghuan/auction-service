//package com.ocbc.auctionservice.controllers;
//
//import com.ocbc.auctionservice.services.HealthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("api/v1/health")
//public class HealthController {
//
//    @Autowired
//    private HealthService healthService;
//
//    @GetMapping
//    public ResponseEntity<String> health(){
//        return ResponseEntity.ok(healthService.healthCheck());
//    }
//}
