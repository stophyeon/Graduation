package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Chatting;
import org.example.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public void saveChat(Chatting chatting){
        chatRepository.save(chatting);
    }
}
