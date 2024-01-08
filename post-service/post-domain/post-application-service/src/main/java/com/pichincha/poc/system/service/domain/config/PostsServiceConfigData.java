package com.pichincha.poc.system.service.domain.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "post-service")
public class PostsServiceConfigData {
    private String postsRequestTopicName;
    private String postsResponseTopicName;
}
