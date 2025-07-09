package com.kh.login.service;

import com.kh.login.domain.ChatParticipant;
import com.kh.login.domain.ChatRoom;
import com.kh.login.domain.Member;
import com.kh.login.repository.ChatParticipantRepository;
import com.kh.login.repository.ChatRoomReposigory;
import com.kh.login.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {

    private final MemberRepository memberRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final ChatRoomReposigory chatRoomReposigory;

    //1:1채팅방 생성 또는 조회
    public Long getOrCreatePrivateRoom(Long otherMemberId) {
        //현재 로그인한 사용자와 상대방 사용자 조회
        Member member = memberRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        Member otherMember = memberRepository.findById(otherMemberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        //기존 1:1채팅방 존재 여부
        Optional<ChatRoom> chatRoom = chatParticipantRepository.findExistingPrivateRoom(member.getId(),
                otherMember.getId());
        if (chatRoom.isPresent()) {
            return chatRoom.get().getId();
        }

        ChatRoom newRoom = ChatRoom.builder()
                .isGroupChat("N")
                .name(member.getName() + "_" + otherMember.getName()) //채팅방 이름
                .build();

        chatRoomReposigory.save(newRoom);

        addParticipantToRoom(newRoom, member);
        addParticipantToRoom(newRoom, otherMember);

        return newRoom.getId();
    }

    public void addParticipantToRoom(ChatRoom chatRoom, Member member) {
        if (chatParticipantRepository.findByChatRoomAndMember(chatRoom, member).isPresent()) {
            return;
        }

        //새로운 참가자 생성
        ChatParticipant chatParticipant = ChatParticipant.builder()
                .chatRoom(chatRoom)
                .member(member)
                .build();
        chatParticipantRepository.save(chatParticipant);
    }
}
