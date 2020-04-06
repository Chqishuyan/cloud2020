package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author shuyan.qi
 * @date 2020-04-05 21:41
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//使RestTemplate支持负载均衡
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }
}
