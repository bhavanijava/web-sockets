package com.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @MessageMapping("/send-message")
    @SendTo("/topic/public")
    public Object sendMessage(Object message) {
        return message;
    }
}
