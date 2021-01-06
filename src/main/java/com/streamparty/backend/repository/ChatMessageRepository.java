package com.streamparty.backend.repository;

import java.util.Optional;

import com.streamparty.backend.model.ChatMessage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {

    public ChatMessage findById(long id);

    public Page<ChatMessage> findByRoomRoomId(String roomId, Pageable pageable);

    public Optional<ChatMessage> findByIdAndRoomRoomId(Long id, String roomId);
    
}
