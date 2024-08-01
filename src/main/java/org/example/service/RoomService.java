package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomDto;
import org.example.entity.ChatRoom;
import org.example.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public String createRoom(RoomDto roomDto){
        ChatRoom chatRoom=roomRepository.save(ChatRoom.builder().roomName(roomDto.getRoomName()).build());
        return chatRoom.getRoomName()+" 채팅방 생성";
    }
    public List<RoomDto> getAll(){
        return roomRepository.findAll().stream().map(ChatRoom::toDto).toList();
    }

}
