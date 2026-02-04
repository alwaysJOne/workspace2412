package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글-태그 매핑 정보를 저장하는 엔티티 클래스 (중간 테이블)
 * Board와 Tag 엔티티 간의 다대다(N:M) 관계를 1:N, N:1로 풀어서 매핑
 * 복합키 대신 인조키(BOARD_TAG_ID)를 사용하여 관리
 */
@Entity
@Table(name = "BOARD_TAG")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_TAG_ID")
    private Long boardTagId;

    // ===== 연관관계 매핑 =====

    // 게시글태그 : 게시글 (N : 1) - 연관관계의 주인 (BOARD_NO FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_NO", nullable = false)
    private Board board;

    // 게시글태그 : 태그 (N : 1) - 연관관계의 주인 (TAG_ID FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;

    // ===== 연관관계 편의 메서드 =====

    /**
     * 게시글 설정 (양방향 관계 설정)
     */
    public void changeBoard(Board board) {
        this.board = board;
        // 무한 루프 방지를 위한 체크
        if(!board.getBoardTags().contains(this)) {
            board.getBoardTags().add(this);
        }
    }

    /**
     * 태그 설정 (양방향 관계 설정)
     */
    public void changeTag(Tag tag) {
        this.tag = tag;
        // 무한 루프 방지를 위한 체크
        if(!tag.getBoardTags().contains(this)) {
            tag.getBoardTags().add(this);
        }
    }

    /**
     * 게시글과 태그를 함께 설정 (양방향 관계 설정)
     */
    public void setBoardAndTag(Board board, Tag tag) {
        changeBoard(board);
        changeTag(tag);
    }
}
