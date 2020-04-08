package com.atguigu.springcloud.loanBalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author shuyan.qi
 * @date 2020-04-08 12:53
 */
@Component("myILoadBalancer")
public class MyILoadBalancer implements ILoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);//接口调用次数

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        if(serviceInstances == null || serviceInstances.size() == 0)
            return null;
        if(serviceInstances.size() == 1)
            return serviceInstances.get(0);

        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    /**
     * 返回新的接口调用次数
     * @return
     */
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current == Integer.MAX_VALUE ? 0:current+1;
        }while (!this.atomicInteger.compareAndSet(current, next));
        return next;
    }
}
