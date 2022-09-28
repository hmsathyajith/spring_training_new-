package com.example.demo_sec.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello everyone ...!!!");
    }

    @GetMapping("/user")
    public ResponseEntity<?> helloUser() {
        return ResponseEntity.ok("Hello User. you are checked...!!!");
    }

    @GetMapping("/admin")
    public ResponseEntity<?> helloAdmin() {
        return ResponseEntity.ok("Hello Admin. you are checked...!!!");
    }

}