package com.lc.homepage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuchun
 * @Package com.lc.homepage.controller
 * @date 2021/3/11 23:16
 * description:
 */
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;
    @StreamListener(Sink.INPUT)
    public void input(Message<String > message){
        System.out.println("消费者2号------》接收到的消息： "+message.getPayload()+"\t port: "+serverPort);
    }

}

