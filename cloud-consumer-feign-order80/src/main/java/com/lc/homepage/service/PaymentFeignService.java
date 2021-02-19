package com.lc.homepage.service;

import com.lc.homepage.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/userList")//微服务对外暴露的接口地址
    CommonResult userList();

    @GetMapping(value = "/payment/feign/timeout")//微服务对外暴露的接口地址
    String paymentfeigntimeout();

}
