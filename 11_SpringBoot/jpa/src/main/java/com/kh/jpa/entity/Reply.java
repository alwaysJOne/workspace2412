package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 댓글 정보를 저장하는 엔티티 클래스
 * BaseTimeEntity를 상속받아 생성일(CREATE_DATE), 수정일(MODIFY_DATE)을 자동 관리
 * Board 엔티티와 N:1 관계
 * Member 엔티티와 N:1 관계
 */
@Entity
@Table(name = "REPLY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private Long replyNo;

    @Column(name = "REPLY_CONTENT", length = 400, nullable = false)
    private String replyContent;

    @Column(name = "STATUS", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CommonEnums.Status status = CommonEnums.Status.Y;

    // ===== 연관관계 매핑 =====

    // 댓글 : 게시글 (N : 1) - 연관관계의 주인 (REF_BNO FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_BNO", nullable = false)
    private Board board;

    // 댓글 : 회원 (N : 1) - 연관관계의 주인 (REPLY_WRITER FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLY_WRITER", nullable = false)
    private Member member;

    // ===== 연관관계 편의 메서드 =====

    /**
     * 댓글이 속한 게시글 설정 (양방향 관계 설정)
     */
    public void changeBoard(Board board) {
        this.board = board;
        // 무한 루프 방지를 위한 체크
        if(!board.getReplies().contains(this)) {
            board.getReplies().add(this);
        }
    }

    /**
     * 댓글 작성자 설정 (양방향 관계 설정)
     */
    public void changeMember(Member member) {
        this.member = member;
        // 무한 루프 방지를 위한 체크
        if(!member.getReplies().contains(this)) {
            member.getReplies().add(this);
        }
    }

    // ===== 비즈니스 로직 =====

    /**
     * 댓글 내용 수정
     */
    public void updateContent(String replyContent) {
        if(replyContent != null && !replyContent.isEmpty()) {
            this.replyContent = replyContent;
        }
    }

    /**
     * 댓글 삭제 (상태 변경)
     */
    public void delete() {
        this.status = CommonEnums.Status.N;
    }

    /**
     * 엔티티 저장 전 실행 (기본값 설정)
     * 생성일은 BaseTimeEntity에서 자동 관리
     */
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = CommonEnums.Status.Y;
        }
    }
} 