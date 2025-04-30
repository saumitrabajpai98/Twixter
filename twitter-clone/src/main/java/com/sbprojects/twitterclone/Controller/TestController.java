package com.sbprojects.twitterclone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> testApi() {
        System.out.println("CORS is correct");
        return ResponseEntity.ok("Backend is working ✅");
    }
}
