package com.lc.homepage.lb.impl;

import com.lc.homepage.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl  implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取访问次数
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.err.println("next:"+next);
        return next;
    }

    //负载均衡算法 rest接口第几次请求数 % 服务器总集群数 = 实际调用服务器位置下标，每次重启服务器后rest接口数从1开始
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        //应该访问的机器下标
        int index = getAndIncrement() % serviceInstances.size();
        //获取提供服务的下标为index的机器
        return serviceInstances.get(index);
    }
}
