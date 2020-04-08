package com.atguigu.springcloud.feignService;

import com.atguigu.spring.cloud.constants.FeignUrlConstants;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "cloud-payment-service")
public interface FeignPaymentService {
    @GetMapping(value = FeignUrlConstants.PAYMENT_GET)
    public CommonResult<Payment> get(@PathVariable("id") Long id);
}
