package com.atguigu.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author shuyan.qi
 * @date 2020-04-05 18:21
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker//支持断路器
//@EnableDiscoveryClient//该注解用于向使用了zookeeper或consul作为注册中心时注册服务
public class CloudProviderPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderPaymentMain8001.class, args);
    }

    /**
     * 此配置是为了服务被监控而配置，与服务容错本身无关，springcloud升级后的坑
     * 因为springboot的默认路径不是"/hystirx.stream"，需要自己主动设置
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(hystrixMetricsStreamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }
}
