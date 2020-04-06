package com.atguitu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shuyan.qi
 * @date 2020-04-06 11:55
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServerMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServerMain7002.class, args);
    }
}
