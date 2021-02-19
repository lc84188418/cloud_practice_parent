package com.lc.homepage.service;

import com.lc.homepage.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liuchun
 * @createDate 2020/10/26 15:54
 * @Classname PaymentHystrixService
 * @Description TODO
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{username}")
    public String paymentInfo_ok(@PathVariable("username") String username);

    @GetMapping(value = "/payment/hystrix/error/{username}")
    public String paymentInfo_timeout2(@PathVariable("username") String username);
}
