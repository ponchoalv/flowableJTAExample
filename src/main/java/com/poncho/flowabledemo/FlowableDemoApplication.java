package com.poncho.flowabledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@ComponentScan
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class FlowableDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableDemoApplication.class, args);
    }

}