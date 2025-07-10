package com.kh.login.controller;

import com.kh.login.dto.chat.ChatMessageDto;
import com.kh.login.dto.chat.ChatRoomResponse;
import com.kh.login.dto.chat.MyChatResponse;
import com.kh.login.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/chat")
@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/room/private/create")
    public ResponseEntity<?> getOrCreatePrivateRoom(@RequestParam("other_member_id") Long otherMemberId) {
        Long roomId = chatService.getOrCreatePrivateRoom(otherMemberId);
        return new ResponseEntity<>(roomId, HttpStatus.OK);
    }

    //특정 채팅방의 이전 메세지 목록 조회
    @GetMapping("/history/{roomId}")
    public ResponseEntity<?> getChatHistory(@PathVariable Long roomId) {
        List<ChatMessageDto> chatMessageDtos = chatService.getChatHistory(roomId);
        return new ResponseEntity<>(chatMessageDtos, HttpStatus.OK);
    }

    //그룹채팅목록 조회
    @GetMapping("/room/group/list")
    public ResponseEntity<?> getChatGroupList() {
        List<ChatRoomResponse> chatRooms = chatService.getGroupchatRooms();
        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }

    //그룹 채팅방 개설
    @PostMapping("/room/group/create")
    public ResponseEntity<?> createGroupRoom(@RequestParam String roomName) {
        Long roomId = chatService.createGroupRoom(roomName);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomId);
    }

    //그룹 채팅방 참여
    @PostMapping("/room/group/{roomId}/join")
    public ResponseEntity<?> joinGroupRoom(@PathVariable Long roomId) {
        chatService.addParticipantToGroupChat(roomId);
        return ResponseEntity.ok().build();
    }

    //내 채팅방 목록 조회 : roomId, roomName, 그룹채팅여부, 메세지 읽을 개수
    @GetMapping("/my/rooms")
    public ResponseEntity<?> getMyRooms() {
        List<MyChatResponse> myChatResponses = chatService.getMyChatRooms();
        return new ResponseEntity<>(myChatResponses, HttpStatus.OK);
    }

    //채팅메세지 읽음 처리
    @PostMapping("/room/{roomId}/read")
    public ResponseEntity<?> readRoom(@PathVariable Long roomId) {
        chatService.messageRead(roomId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/room/group/{roomId}/leave")
    public ResponseEntity<?> leaveGroupChatRoom(@PathVariable Long roomId) {
        chatService.leaveGroupChatRoom(roomId);
        return ResponseEntity.ok().build();
    }
}
