package com.lc.homepage.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义lb
 */
public interface LoadBalancer {
    //第一步收集，eureka服务上活着的提供调用的微服务，参数List<ServiceInstance> serviceInstances为总机器数
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
