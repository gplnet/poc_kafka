package com.pichincha.poc.system.post.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.pichincha.poc.system.dataaccess")
@EntityScan(basePackages = "com.pichincha.poc.system.dataaccess")
@SpringBootApplication(scanBasePackages = "com.pichincha.poc.system")
public class PostServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostServiceApplication.class, args);
    }
}
