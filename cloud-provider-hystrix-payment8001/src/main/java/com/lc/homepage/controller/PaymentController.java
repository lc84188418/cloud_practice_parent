package com.lc.homepage.controller;

import com.lc.homepage.entity.CommonResult;
import com.lc.homepage.entity.User;
import com.lc.homepage.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuchun
 * @createDate 2020/10/26 12:02
 * @Classname PaymentController
 * @Description TODO
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{username}")
    public String paymentInfo_ok(@PathVariable("username") String username){
        String result = paymentService.paymentInfo_ok(username);
        return result;
    }
    @GetMapping(value = "/payment/hystrix/error/{username}")
    public String paymentInfo_timeout(@PathVariable("username") String username){
        String result = paymentService.paymentInfo_timeout2(username);
        return result;
    }
    //服务熔断
    @GetMapping(value = "/payment/hystrix/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") int id){
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
