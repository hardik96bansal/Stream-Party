package com.streamparty.backend.service;

import com.streamparty.backend.model.ChatMessage;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.repository.ChatMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    public ChatMessage getChatMessageById(long id){
        return chatMessageRepository.findById(id);
    }

    public void createChatMessage(String message, User sender, Room room){
        ChatMessage chatMessage = new ChatMessage(message, sender, room);
        chatMessageRepository.save(chatMessage);
    }

    public Page<ChatMessage> getAllMessagesByRoomId(String roomId, Pageable pageable){
        return chatMessageRepository.findByRoomRoomId(roomId, pageable);
    }

    
    
}
