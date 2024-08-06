package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.Message;
import org.example.entity.Chatting;
import org.example.repository.ChatRepository;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {


    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;
    private final ChatRepository chatRepository;


    public void pubMsgChannel(String channel ,Message message) {
        //1. 요청한 Channel 을 구독.
        log.info("구독");
        redisMessageListenerContainer.addMessageListener(redisSubscriber, new ChannelTopic("room"+channel));
        //2. Message 전송
        log.info("전송");
        redisPublisher.publish(new ChannelTopic("room"+channel), message);
        log.info("저장");
        chatRepository.save(Message.toEntity(message));
    }



}
