package com.lc.homepage;

import com.lc.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)//规定负载均衡算法规则
public class OrderMain80 {

    public static void main(String []args){
        SpringApplication.run(OrderMain80.class,args);
    }
}
