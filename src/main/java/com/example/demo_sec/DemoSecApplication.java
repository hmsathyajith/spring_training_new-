package com.example.demo_sec;

import com.example.demo_sec.service.CustomUserDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class DemoSecApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSecApplication.class, args);
    }

}
