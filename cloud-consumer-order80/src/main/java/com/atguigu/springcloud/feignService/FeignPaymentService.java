package com.atguigu.springcloud.feignService;

import com.atguigu.spring.cloud.constants.FeignUrlConstants;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "cloud-payment-service",fallback = PaymentFallbackService.class)
public interface FeignPaymentService {
    @GetMapping(value = FeignUrlConstants.PAYMENT_GET)
    CommonResult<Payment> get(@PathVariable("id") Long id);

    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_OK)
    String hystrix_ok(@PathVariable("id") Integer id);

    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_TIMEOUT)
    String hystrix_timeout(@PathVariable("id") Integer id);

    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_ERROR)
    String hystrix_error();
}
