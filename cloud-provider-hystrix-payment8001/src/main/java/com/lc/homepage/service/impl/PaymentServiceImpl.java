package com.lc.homepage.service.impl;

import cn.hutool.core.util.IdUtil;
import com.lc.homepage.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author liuchun
 * @createDate 2020/10/26 11:51
 * @Classname PaymentServiceImpl
 * @Description TODO
 */
@Service
@DefaultProperties(defaultFallback = "payment_global_fallback")
public class PaymentServiceImpl implements PaymentService {
    //1.1-----------------服务降级-------------------------------------
    /**
     *正常访问，OK的方法
     * @param username
     * @return
     */
    @Override
    public String paymentInfo_ok(String username) {
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_ok,username: "+username+"\t"+"O(∩_∩)O哈哈~";
    }

    /**
     *出错访问，error的方法
     * @param username
     * @return
     */
    @Override
    public String paymentInfo_timeout(String username) {
        int timeout = 5;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_timeout,username: "+username+"\t"+"O(∩_∩)O哈哈~"+"耗时3秒钟";
    }

    //1.2------------------------------------------------------
    /**
     * @HystrixCommand开启服务降级，对应主启动类注解@EnableCircuitBreaker//激活服务降级
     * 服务降级示例
     *出错访问，error的方法
     * @param username
     * @return
     */
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
//    })
    @HystrixCommand
    @Override
    public String paymentInfo_timeout2(String username) {
//        int a = 10/0;
        int timeout = 3;
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+" 8001支付系统正常,username: "+username+"\t"+"O(∩_∩)O哈哈~";
    }
    //当某服务（paymentInfo_timeout2）失败，就会调用兜底的fallback方法
    public String paymentInfo_timeout_fallback(String username) {
        return "线程池: "+Thread.currentThread().getName()+" 8001支付系统繁忙,username: "+username+"\t"+"o(╥﹏╥)o";
    }

    //1.3------------------------------------------------------
    //下面是全局fallback
    public String payment_global_fallback() {
        return "线程池: "+Thread.currentThread().getName()+" 8001-global全局异常处理信息，"+"o(╥﹏╥)o";
    }

    //二----------服务熔断--------------------------------------------

    /**
     * 请求总数阈值:requestVolumeThreshold
     *  在快照时间窗内，必须满足请求总数阈值才有资格熔断，默认为20，意味着在10秒内，如果该hystrix命令的调用次数不足20次，
     *  即使所有的请求都超时或因为其他原因失败，断路器都不会打开
     * 快照时间窗:sleepWindowInMilliseconds
     *  断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒
     * 错误百分比阈值:errorThresholdPercentage
     *  当请求总数在时间窗口期内超过阈值，比如10秒钟内发生10次调用，其中5次发生异常，错误百分比就超过50%，在默认设定50%阈值的情况下，断路器就会打开
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求总数阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//快照时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少次后跳闸

    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id") int id){
        if(id<0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNum = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNum;
    }

    public String paymentCircuitBreaker_fallBack(@PathVariable("id") int id){
        return "id不能为负数，请稍后再试";
    }
}
