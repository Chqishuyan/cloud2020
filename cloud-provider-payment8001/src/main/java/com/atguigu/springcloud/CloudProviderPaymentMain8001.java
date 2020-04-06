package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shuyan.qi
 * @date 2020-04-05 18:21
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient//该注解用于向使用了zookeeper或consul作为注册中心时注册服务
public class CloudProviderPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentMain8001.class, args);
    }
}
