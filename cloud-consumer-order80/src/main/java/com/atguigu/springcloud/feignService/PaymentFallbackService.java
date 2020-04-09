package com.atguigu.springcloud.feignService;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * @author shuyan.qi
 * @date 2020-04-09 20:14
 */
@Component
public class PaymentFallbackService implements FeignPaymentService{
    @Override
    public CommonResult<Payment> get(Long id) {
        CommonResult<Payment> commonResult = new CommonResult<>();
        commonResult.setCode(CommonResult.ERROR_CODE);
        commonResult.setMessage(fallback());
        return commonResult;
    }

    @Override
    public String hystrix_ok(Integer id) {
        return fallback();
    }

    @Override
    public String hystrix_timeout(Integer id) {
        return fallback();
    }

    @Override
    public String hystrix_error() {
        return fallback();
    }

    public static String fallback(){
        return "支付系统异常,请稍后重试";
    }
}
