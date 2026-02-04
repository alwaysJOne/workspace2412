package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 회원 정보를 저장하는 엔티티 클래스
 * BaseTimeEntity를 상속받아 생성일(ENROLL_DATE), 수정일(MODIFY_DATE)을 자동 관리
 */
@Entity
@Table(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@SuperBuilder
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PWD", length = 100, nullable = false)
    private String userPwd;

    @Column(name = "USER_NAME", length = 15, nullable = false)
    private String userName;

    @Column(name = "EMAIL", length = 254)
    private String email;

    @Column(name = "GENDER", length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "PHONE", length = 13)
    private String phone;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @Column(name = "STATUS", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private CommonEnums.Status status = CommonEnums.Status.Y;

    // ===== 연관관계 매핑 =====

    // 회원 : 프로필 (1 : 1) - 연관관계의 주인은 Profile (Profile이 USER_ID FK를 가짐)
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    // 회원 : 게시글 (1 : N) - 연관관계의 주인은 Board (Board가 BOARD_WRITER FK를 가짐)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    // 회원 : 공지사항 (1 : N) - 연관관계의 주인은 Notice (Notice가 NOTICE_WRITER FK를 가짐)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Notice> notices = new ArrayList<>();

    // 회원 : 댓글 (1 : N) - 연관관계의 주인은 Reply (Reply가 REPLY_WRITER FK를 가짐)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    // ===== 내부 Enum =====

    /**
     * 성별 정보 (M: 남성, F: 여성)
     */
    public enum Gender {
        M, F
    }

    // ===== 비즈니스 로직 =====

    /**
     * 회원 정보 수정 메서드
     */
    public void updateMemberInfo(String userName, String email, Gender gender, String phone, String address, Integer age) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    /**
     * 엔티티 저장 전 실행 (status 기본값 설정)
     * 생성일, 수정일은 BaseTimeEntity에서 자동 관리
     */
    @PrePersist
    public void prePersist() {
        if(this.status == null) {
            this.status = CommonEnums.Status.Y;
        }
    }
}
