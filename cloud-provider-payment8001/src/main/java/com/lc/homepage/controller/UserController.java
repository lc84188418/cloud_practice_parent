package com.lc.homepage.controller;

import com.lc.homepage.entity.CommonResult;
import com.lc.homepage.entity.User;
import com.lc.homepage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j//打印日志
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/getDiscovery")
    public Object getDiscovery(){
        //获取所有微服务列表
        List<String> services = discoveryClient.getServices();
        if(!services.isEmpty()){
            for(String s : services){
                log.info("**element:"+s);
            }
        }
        //获取该微服务下的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(!instances.isEmpty()){
            for(ServiceInstance s : instances){
                log.info("**instances:"+s.getInstanceId()+"\t"+s.getHost()+"\t"+s.getPort()+"\t"+s.getUri());
            }
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping(value = "/payment/userList")
    public CommonResult userList(){
        List<User> userList = userService.queryall();
        if(!userList.isEmpty()){
            return new CommonResult(200,"success","serverPort:"+serverPort+",查询数据成功",userList);
        }else {
            return new CommonResult(444,"error","serverPort:"+serverPort+",查询数据失败",null);
        }
    }
    @GetMapping(value = "/payment/userGet/{username}")
    public CommonResult userGet(@PathVariable("username") String username){
        User user = userService.userGetByUserName(username);
        log.info("查询结果："+user);
        if(user != null){
            return new CommonResult(200,"success","serverPort:"+serverPort+"，查询数据成功",user);
        }else {
            return new CommonResult(444,"error","serverPort:"+serverPort+"，查询数据失败",null);
        }
    }
}
