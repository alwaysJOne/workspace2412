package com.kh.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 공지사항 정보를 저장하는 엔티티 클래스
 * CREATE_DATE만 있어 BaseTimeEntity를 상속받지 않고 직접 관리
 */
@Getter
@Entity
@Table(name = "NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // Auditing 기능으로 생성일 자동 관리
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NO")
    private Long noticeNo;

    @Column(name = "NOTICE_TITLE", length = 30, nullable = false)
    private String noticeTitle;

    @Column(name = "NOTICE_CONTENT", length = 200, nullable = false)
    private String noticeContent;

    @CreatedDate // 생성 시간 자동 설정
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;

    // ===== 연관관계 매핑 =====

    // 공지사항 : 회원 (N : 1) - 연관관계의 주인 (NOTICE_WRITER FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NOTICE_WRITER", nullable = false)
    private Member member;

    // ===== 연관관계 편의 메서드 =====

    /**
     * 공지사항 작성자 설정 (양방향 관계 설정)
     */
    public void changeMember(Member member) {
        this.member = member;
        // 무한 루프 방지를 위한 체크
        if(!member.getNotices().contains(this)) {
            member.getNotices().add(this);
        }
    }

    // ===== 비즈니스 로직 =====

    /**
     * 공지사항 제목 수정
     */
    public void changeTitle(String noticeTitle) {
        if(noticeTitle != null && !noticeTitle.isEmpty()) {
            this.noticeTitle = noticeTitle;
        }
    }

    /**
     * 공지사항 내용 수정
     */
    public void changeContent(String noticeContent) {
        if(noticeContent != null && !noticeContent.isEmpty()) {
            this.noticeContent = noticeContent;
        }
    }

    /**
     * 공지사항 전체 수정
     */
    public void updateNotice(String noticeTitle, String noticeContent) {
        changeTitle(noticeTitle);
        changeContent(noticeContent);
    }
}
