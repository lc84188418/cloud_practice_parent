package com.lc.homepage.service.impl;

import com.lc.homepage.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author liuchun
 * @createDate 2020/10/28 9:37
 * @Classname PaymentHystrixServiceImpl
 * @Description 为实现的接口PaymentHystrixService 里的每一个方法写fallback方法
 */

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_ok(String username) {
        return "PaymentFallbackService fall back-paymentInfo_ok";
    }

    @Override
    public String paymentInfo_timeout2(String username) {
        return "PaymentFallbackService fall back-paymentInfo_timeout2";
    }
}
