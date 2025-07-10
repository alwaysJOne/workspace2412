package com.kh.login.repository;

import com.kh.login.domain.ChatRoom;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomReposigory extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByIsGroupChat(String isGroupChat);
}
