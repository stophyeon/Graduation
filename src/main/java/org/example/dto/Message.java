package org.example.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.entity.Chatting;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private String sender;
    private String content;
    private MessageType type; // ENTER,TALK
    private String chatRoomId;

    public static Chatting toEntity(Message message){
        return Chatting.builder()
                .roomId(message.getChatRoomId())
                .content(message.getContent())
                .senderName(message.getSender())
                .build();
    }
}
