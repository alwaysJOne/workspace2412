package com.kh.login.repository;

import com.kh.login.domain.ChatRoom;
import com.kh.login.domain.Member;
import com.kh.login.domain.ReadStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadStatusRepository extends JpaRepository<ReadStatus, Long> {
    Long countByChatRoomAndMemberAndIsReadFalse(ChatRoom chatRoom, Member member);

    List<ReadStatus> findByChatRoomAndMemberAndIsReadFalse(ChatRoom chatRoom, Member member);
}
