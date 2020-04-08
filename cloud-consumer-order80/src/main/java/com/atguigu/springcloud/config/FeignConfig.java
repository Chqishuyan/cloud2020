package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shuyan.qi
 * @date 2020-04-08 22:40
 */
@Configuration
public class FeignConfig {


    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;

    }

}
