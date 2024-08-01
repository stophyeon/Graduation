package org.example.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.example.dto.RoomDto;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="room")
@Getter
public class ChatRoom {
    @Id
    private String roomId;
    private String roomName;
    private int userCount;

    @Builder
    public ChatRoom(String roomName){
        this.roomName=roomName;
    }

    public static RoomDto toDto(ChatRoom chatRoom){
        return RoomDto.builder().roomName(chatRoom.getRoomName()).build();
    }
}
