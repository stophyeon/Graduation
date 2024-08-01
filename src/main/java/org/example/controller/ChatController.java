package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Message;
import org.example.service.ChatService;
import org.example.service.RedisPublisher;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/chat")
public class ChatController {
    private final ChatService chatService;
    private final RedisPublisher redisPublisher;

    @MessageMapping("/chat/message")
    public void message(Message message) {
        chatService.saveChat(Message.toEntity(message));
        redisPublisher.publish("chatroom",message.getContent()); //RedisPublisher 호출
    }









}
