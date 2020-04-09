package com.atguigu.springcloud.web;

import com.atguigu.spring.cloud.constants.FeignUrlConstants;
import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.fallback.DefaultFallBack;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shuyan.qi
 * @date 2020-04-05 19:25
 */
@RestController
@Slf4j
public class PaymentController extends DefaultFallBack {
    @Value("${server.port}")
    private Integer serverPort;

    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if(result > 0){
            return new CommonResult(200, "插入成功,serverPort="+serverPort);
        }else {
            return new CommonResult(400, "插入失败,serverPort="+serverPort);
        }
    }

    //@GetMapping(value = "/payment/get/{id}")
    @GetMapping(value = FeignUrlConstants.PAYMENT_GET)
    public CommonResult getById(@PathVariable("id") Long id){
        log.info(FeignUrlConstants.PAYMENT_GET);
        Payment payment = paymentService.findPaymentById(id);
        try {
            TimeUnit.SECONDS.sleep(2);//演示请求超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(payment != null){
            return new CommonResult(200,"success,serverPort="+serverPort,payment);
        }else{
            return new CommonResult(400,"查询失败,serverPort="+serverPort);
        }
    }

    @GetMapping(value = "discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String serviceId :services) {
            log.info("serviceId=" + serviceId);
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance instance :instances) {
                log.info("***** instance info: "+instance.getInstanceId()+"\t"
                        +instance.getServiceId()+"\t"
                        +instance.getHost()+"\t"
                        +instance.getPort()
                        +"\t"+instance.getUri());
            }
        }
        return discoveryClient;
    }

    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_OK)
    public String hystrix_ok(@PathVariable("id") Integer id){
        log.info("hystrix_ok id={}",id);
        return "hystrix_ok";
    }

    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_TIMEOUT)
    @HystrixCommand(fallbackMethod = "hystrix_timeout_rollback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String hystrix_timeout(@PathVariable("id") Integer id){
        log.info("hystrix_timeout");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hystrix_timeout";
    }

    public String hystrix_timeout_rollback(Integer id){
        return "hystrix超时降级服务...";
    }


    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_ERROR)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String hystrix_error(){
        log.info("hystrix_error");
        int i = 10/0;
        return "hystrix_error";
    }

}
