package com.streamparty.backend.controller;

import com.streamparty.backend.model.ChatMessage;
import com.streamparty.backend.model.Room;
import com.streamparty.backend.model.User;
import com.streamparty.backend.service.ChatMessageService;
import com.streamparty.backend.service.RoomService;
import com.streamparty.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatMessageController {

    @Autowired
    ChatMessageService chatMessageService;

    @Autowired
    UserService userService;

    @Autowired 
    RoomService roomService;

    @GetMapping("/chat/{chatId}")
    public ChatMessage getChatById(@PathVariable("chatId") String chatId){
        long chatIdLong = Long.parseLong(chatId);
        return chatMessageService.getChatMessageById(chatIdLong);
    }

    @GetMapping("/room/{roomId}/chat")
    public Page<ChatMessage> getAllChatByRoomId(@PathVariable("roomId") String roomId, Pageable pageable){
        return chatMessageService.getAllMessagesByRoomId(roomId,pageable);
    }

    @PostMapping("/chat/{roomId}/{username}")
    public ResponseEntity<String> createChatMessage(@PathVariable("roomId") String roomId, @PathVariable("username") String username,
                                @RequestBody String chatMessage){

        User sender = userService.getUserByUsername(username);
        Room room = roomService.getRoomById(roomId);  
        
        if(sender==null || room==null){
            return new ResponseEntity<>("Invalid data passed",HttpStatus.BAD_REQUEST);
        }
        chatMessageService.createChatMessage(chatMessage, sender, room);
        return new ResponseEntity<>("Message created",HttpStatus.OK);
    }
    
}
