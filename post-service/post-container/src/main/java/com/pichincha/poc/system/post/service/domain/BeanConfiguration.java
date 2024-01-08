package com.pichincha.poc.system.post.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PostDomainService postDomainService(){ return new PostDomainServiceImpl();}
}
