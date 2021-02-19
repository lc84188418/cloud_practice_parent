package com.lc.homepage.service;

/**
 * @author liuchun
 * @createDate 2020/10/26 11:51
 * @Classname PaymentService
 * @Description TODO
 */

public interface PaymentService {

    public String paymentInfo_ok(String username);
    public String paymentInfo_timeout(String username);
    public String paymentInfo_timeout2(String username);

    String paymentCircuitBreaker(int id);
}
