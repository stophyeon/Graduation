package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.RoomDto;
import org.example.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("")
    public String makeRoom(@RequestBody RoomDto roomDto){
        return roomService.createRoom(roomDto);
    }
    @GetMapping("")
    public List<RoomDto> getRooms(){
        return roomService.getAll();
    }

}
