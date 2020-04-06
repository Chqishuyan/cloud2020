package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author shuyan.qi
 * @date 2020-04-05 20:55
 */
@SpringBootApplication
@EnableEurekaClient
public class CloudConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrderMain80.class, args);
    }
}
