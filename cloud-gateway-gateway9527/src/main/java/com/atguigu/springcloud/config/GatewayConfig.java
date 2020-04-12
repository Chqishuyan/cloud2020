package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author shuyan.qi
 * @date 2020-04-11 13:39
 */
@Configuration
public class GatewayConfig {

    /*@Bean
    public RouteLocator myRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        Builder routes = routeLocatorBuilder.routes();
        routes.route("payment-service-1", r -> r.path("/payment/hystrix/ok/**").uri("http://localhost:8001"));
        routes.route("payment-service-2", r -> r.path("/payment/get/**").uri("http://localhost:8002"));
        return routes.build();
    }*/
}
