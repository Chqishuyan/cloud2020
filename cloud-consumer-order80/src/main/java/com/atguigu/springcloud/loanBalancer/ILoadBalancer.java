package com.atguigu.springcloud.loanBalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface ILoadBalancer {

    /**
     * 返回处理该次请求的服务
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
