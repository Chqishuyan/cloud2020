package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author shuyan.qi
 * @date 2020-04-05 18:21
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class CloudProviderPaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentMain8002.class, args);
    }
}
