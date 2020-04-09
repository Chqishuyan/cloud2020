package com.atguigu.springcloud.fallback;

import com.atguigu.springcloud.feignService.PaymentFallbackService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author shuyan.qi
 * @date 2020-04-09 20:31
 */
@Component
public class PaymentFallbackFactory implements FallbackFactory<PaymentFallbackService>{
    @Override
    public PaymentFallbackService create(Throwable throwable) {
        PaymentFallbackService paymentFallbackService = new PaymentFallbackService();
        return paymentFallbackService;
    }
}
