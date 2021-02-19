package com.lc.homepage.controller;

import com.lc.homepage.entity.CommonResult;
import com.lc.homepage.entity.User;
import com.lc.homepage.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;


@RestController
@Slf4j
public class OrderController {

//    public static final String USER_URL = "http://localhost:8001";
    public static final String USER_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLb(){
        //获取该微服务下的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return "该注册中心中没有微服务";
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
    @GetMapping(value = "/consumer/userList")
    public CommonResult<User> userList(){
        log.info("order正在查询列表：");
        return restTemplate.getForObject(USER_URL+"/payment/userList",CommonResult.class);
    }
    @GetMapping(value = "/consumer/userGet/{username}")
    public CommonResult<User> userGet(@PathVariable("username") String username){
        return restTemplate.getForObject(USER_URL+"/payment/userGet/"+username,CommonResult.class);
    }
    @GetMapping(value = "/consumer/userGetEntity/{username}")
    public CommonResult<User> userGetEntity(@PathVariable("username") String username){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(USER_URL+"/payment/userGet/"+username,CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"error","操作失败",null);
        }
    }
}
