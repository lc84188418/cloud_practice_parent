package com.lc.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换负载均衡默认规则
 */
@Configuration
public class MyselfRule {
    //ribbon核心组件IRule,配置规则
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
