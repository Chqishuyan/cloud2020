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



    //演示服务熔断
    @HystrixCommand(fallbackMethod = "hystrix_circuitBreaker_fallback",commandProperties = {
            //配置解释：在10次请求里如果有60%的请求是失败了的，那么就进行熔断(OPEN)。随后每隔10秒尝试接收请求(HALF_OPEN)，直达完全开放(CLOSE)。
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到该值，熔断
    })
    @GetMapping(value = FeignUrlConstants.PAYMENT_HYSTRIX_CIRCUIT_BREAKER)
    public String hystrix_circuitBreaker(@PathVariable("id")Integer id){
        log.info("hystrix_circuitBreaker id={}",id);
        if (id < 0){
            throw new RuntimeException("id不能为负数");
        }
        //当熔断发生后，会发现id不小于0的请求也会走向降级
        return "hystrix_circuitBreaker";
    }


    public String hystrix_circuitBreaker_fallback(Integer id){
        return "服务熔断-支付系统维护中...";
    }

}
