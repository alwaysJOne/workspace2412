package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 게시글 정보를 저장하는 엔티티 클래스
 * BaseTimeEntity를 상속받아 생성일(CREATE_DATE), 수정일(MODIFY_DATE)을 자동 관리
 */
@Getter
@Entity
@Table(name = "BOARD")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@SuperBuilder
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NO")
    private Long boardNo;

    @Column(name = "BOARD_TITLE", length = 100, nullable = false)
    private String boardTitle;

    // @Lob : 대용량 텍스트 데이터(CLOB) 매핑
    @Column(name = "BOARD_CONTENT", nullable = false)
    @Lob
    private String boardContent;

    @Column(name = "ORIGIN_NAME", length = 100)
    private String originName;

    @Column(name = "CHANGE_NAME", length = 100)
    private String changeName;

    @Column(name = "COUNT")
    @Builder.Default
    private Integer count = 0;

    @Column(name = "STATUS", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CommonEnums.Status status = CommonEnums.Status.Y;

    // ===== 연관관계 매핑 =====

    // 게시글 : 회원 (N : 1) - 연관관계의 주인 (BOARD_WRITER FK를 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_WRITER", nullable = false)
    private Member member;

    // 게시글 : 댓글 (1 : N) - 연관관계의 반대편 (Reply가 REF_BNO FK를 가짐)
    // orphanRemoval = true : 부모와의 관계가 끊어진 자식 엔티티를 자동으로 삭제, 참조기준
    //cascade = CascadeType.ALL은 부모가 삭제되었을 때 자식도 삭제, 부모객체기준
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default //Builder패턴사용시 replies를 초기화해주지 않을 것이기 때문에 NPE발생가능, 빌더사용시 따로 값 안넣으면 이 기본값을 써라.
    private List<Reply> replies = new ArrayList<>();

    // 게시글 : 게시글태그 (1 : N) - 연관관계의 반대편 (BoardTag가 BOARD_NO FK를 가짐)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BoardTag> boardTags = new ArrayList<>();

    // ===== 연관관계 편의 메서드 =====

    /**
     * 게시글 작성자 설정 (양방향 관계 설정)
     */
    public void changeMember(Member member) {
        this.member = member;
        // 무한 루프 방지를 위한 체크
        if(!member.getBoards().contains(this)) {
            member.getBoards().add(this);
        }
    }

    // ===== 비즈니스 로직 =====

    /**
     * 첨부파일 정보 변경
     */
    public void changeFile(String originName, String changeName) {
        this.originName = originName;
        this.changeName = changeName;
    }

    /**
     * 게시글 내용 변경
     */
    public void changeContent(String boardContent) {
        if(boardContent != null && !boardContent.isEmpty()) {
            this.boardContent = boardContent;
        }
    }

    /**
     * 게시글 제목 변경
     */
    public void changeTitle(String boardTitle) {
        if(boardTitle != null && !boardTitle.isEmpty()) {
            this.boardTitle = boardTitle;
        }
    }

    /**
     * 조회수 증가
     */
    public void increaseCount() {
        this.count++;
    }

    /**
     * 엔티티 저장 전 실행 (기본값 설정)
     * 생성일은 BaseTimeEntity에서 자동 관리
     */
    @PrePersist
    protected void onCreate() {
        if(this.count == null) {
            this.count = 0;
        }
        if(this.status == null) {
            this.status = CommonEnums.Status.Y;
        }
    }
}
