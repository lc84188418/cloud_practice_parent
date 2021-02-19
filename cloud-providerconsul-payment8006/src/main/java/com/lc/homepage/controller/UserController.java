package com.lc.homepage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j//打印日志
@RestController
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul(){
        return "spring cloud with consul "+"serverPort:"+serverPort+"\t"+UUID.randomUUID().toString();
    }
}
