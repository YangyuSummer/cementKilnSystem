package com.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wang.dao")
@SpringBootApplication
public class CementCompatibilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CementCompatibilityApplication.class, args);
    }

}
