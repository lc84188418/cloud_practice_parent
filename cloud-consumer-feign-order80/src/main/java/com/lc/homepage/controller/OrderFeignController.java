package com.lc.homepage.controller;

import com.lc.homepage.entity.CommonResult;
import com.lc.homepage.entity.User;
import com.lc.homepage.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/userList")
    public CommonResult userList(){
        return paymentFeignService.userList();
    }
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String feigntimeout(){
        return paymentFeignService.paymentfeigntimeout();
    }
}
