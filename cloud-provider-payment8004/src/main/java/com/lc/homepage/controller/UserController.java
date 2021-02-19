package com.lc.homepage.controller;

import com.lc.homepage.entity.CommonResult;
import com.lc.homepage.entity.User;
import com.lc.homepage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j//打印日志
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public CommonResult paymentZookeeper(){
        return new CommonResult(200,"success","spring cloud with zookeeper"+"serverPort:"+serverPort+"\t"+UUID.randomUUID().toString());
    }
}
