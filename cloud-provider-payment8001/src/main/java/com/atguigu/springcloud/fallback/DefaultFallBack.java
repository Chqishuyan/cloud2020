package com.atguigu.springcloud.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.stereotype.Component;

/**
 * @author shuyan.qi
 * @date 2020-04-09 19:55
 */
@Component
@DefaultProperties(defaultFallback = "defaultFallbackMethod")
public class DefaultFallBack {

    /**
     * hystrix全局降级处理
     * @return
     */
    public String defaultFallbackMethod(){
        return "糟糕,服务器异常,请稍后重试~~~";
    }
}
