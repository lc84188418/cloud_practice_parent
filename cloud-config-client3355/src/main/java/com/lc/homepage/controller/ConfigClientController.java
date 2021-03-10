package com.lc.homepage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchun
 * @Package com.lc.homepage.controller
 * @date 2021/2/24 0:48
 * description:
 */
@RestController
@RefreshScope//开启客户端动态刷新配置
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    @GetMapping()
    public String getConfigInfo(){
        return configInfo;
    }
}
