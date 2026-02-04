package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 프로필 정보를 저장하는 엔티티 클래스
 * Member 엔티티와 1:1 관계
 */
@Getter
@Entity
@Table(name = "PROFILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private Long profileId;

    @Column(name = "PROFILE_IMAGE", length = 100)
    private String profileImage;

    @Column(name = "INTRO", length = 300)
    private String intro;

    // ===== 연관관계 매핑 =====

    // 프로필 : 회원 (1 : 1) - 연관관계의 주인 (USER_ID FK를 가짐)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true, nullable = false)
    private Member member;

    // ===== 연관관계 편의 메서드 =====

    /**
     * 프로필 소유자 설정 (양방향 관계 설정)
     */
    public void changeMember(Member member) {
        this.member = member;
    }

    // ===== 비즈니스 로직 =====

    /**
     * 프로필 이미지 변경
     */
    public void changeProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * 자기소개 변경
     */
    public void changeIntro(String intro) {
        this.intro = intro;
    }

    /**
     * 프로필 정보 전체 수정
     */
    public void updateProfile(String profileImage, String intro) {
        this.profileImage = profileImage;
        this.intro = intro;
    }
}
