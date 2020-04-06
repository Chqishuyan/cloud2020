package com.atguigu.springcloud.web;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author shuyan.qi
 * @date 2020-04-05 19:25
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private Integer serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200, "插入成功,serverPort="+serverPort);
        }else {
            return new CommonResult(400, "插入失败,serverPort="+serverPort);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){
        Payment payment = paymentService.findPaymentById(id);
        if(payment != null){
            return new CommonResult(200,"success,serverPort="+serverPort,payment);
        }else{
            return new CommonResult(400,"查询失败,serverPort="+serverPort);
        }
    }

}
