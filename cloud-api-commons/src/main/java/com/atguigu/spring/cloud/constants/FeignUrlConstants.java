package com.atguigu.spring.cloud.constants;

/**
 * @author shuyan.qi
 * @date 2020-04-08 17:22
 */
public class FeignUrlConstants {
    public static final String PAYMENT_GET = "/payment/get/{id}";
    public static final String PAYMENT_HYSTRIX_OK = "/payment/hystrix/ok/{id}";
    public static final String PAYMENT_HYSTRIX_TIMEOUT = "/payment/hystrix/timeout/{id}";
    public static final String PAYMENT_HYSTRIX_ERROR = "/payment/hystrix/error";
}
