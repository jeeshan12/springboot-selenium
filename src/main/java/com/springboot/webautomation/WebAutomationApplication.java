package com.springboot.webautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.springboot.webautomation"})
@SpringBootApplication
public class WebAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAutomationApplication.class, args);
    }

}
