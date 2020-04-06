package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author shuyan.qi
 * @date 2020-04-06 11:11
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServerMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServerMain7001.class, args);
    }
}
