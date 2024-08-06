package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RoomDto {
    private String roomName;
    private String roomId;
    private int userCount;
    @Builder
    public RoomDto(String roomName,String room_id,int userCount){
        this.roomName=roomName;
        this.roomId=room_id;
        this.userCount=userCount;
    }
}