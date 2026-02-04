package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 태그 정보를 저장하는 엔티티 클래스
 * 게시글과 다대다(N:M) 관계를 가지며, BoardTag를 통해 연결됨
 */
@Entity
@Table(name = "TAG")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long tagId;

    @Column(name = "TAG_NAME", length = 30, nullable = false, unique = true)
    private String tagName;

    // ===== 연관관계 매핑 =====

    // 태그 : 게시글태그 (1 : N) - 연관관계의 반대편 (BoardTag가 TAG_ID FK를 가짐)
    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BoardTag> boardTags = new ArrayList<>();

    // ===== 비즈니스 로직 =====

    /**
     * 태그 이름 수정
     */
    public void changeTagName(String tagName) {
        if(tagName != null && !tagName.isEmpty()) {
            this.tagName = tagName;
        }
    }
} 