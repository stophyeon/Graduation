package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RoomDto {
    private String roomName;

    @Builder
    public RoomDto(String roomName){
        this.roomName=roomName;
    }
}
