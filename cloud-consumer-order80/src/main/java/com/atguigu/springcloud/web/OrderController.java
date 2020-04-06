package com.atguigu.springcloud.web;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shuyan.qi
 * @date 2020-04-05 21:42
 */
@RestController
@Slf4j
public class OrderController {
    //private static final String payment_base_url = "http://localhost:8001";
    //private static final String payment_eureka_url = "http://CLOUD-PAYMENT-SERVICE"; //eureka服务节点

    private static final String payment_zookeeper_url = "http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/order/create")
    public CommonResult create(Payment payment){
        log.info("******* create payment={}",payment);
        //return restTemplate.postForObject(payment_eureka_url+"/payment/create",payment,CommonResult.class);
        return restTemplate.postForObject(payment_zookeeper_url +"/payment/create",payment,CommonResult.class);
    }

    @GetMapping(value = "/order/get/{id}")
    public CommonResult getById(@PathVariable("id")Long id){
        log.info("****** getById id={}",id);
        //return restTemplate.getForObject(payment_eureka_url+"/payment/get/"+id, CommonResult.class);
        return restTemplate.getForObject(payment_zookeeper_url +"/payment/get/"+id, CommonResult.class);
    }
}
