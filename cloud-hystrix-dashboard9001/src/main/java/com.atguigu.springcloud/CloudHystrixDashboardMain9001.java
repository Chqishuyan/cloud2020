package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author shuyan.qi
 * @date 2020-04-11 10:16
 */
@SpringBootApplication
@EnableHystrixDashboard
public class CloudHystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(CloudHystrixDashboardMain9001.class, args);
    }
}
