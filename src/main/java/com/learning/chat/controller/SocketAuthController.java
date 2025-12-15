package com.learning.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@MessageMapping("/auth")
public class SocketAuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
