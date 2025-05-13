package com.kh.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity //이 클래스가 JPA 엔티티다(DB테이블과 맵핑됨)
public class Board {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk값 자동생성(AUTO_INCREMENT방식)
    @Column(name = "board_id") //DB컬럼명 지정
    private Long boardId;

    @Column(nullable = false) //NOT NULL 제약조건
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") //데이터타입을 TEXT로 저장
    private String contents;

    @Column(name = "file_name")
    private String fileName;

//    private String memberEmail;

    @CreationTimestamp //엔티티가 처음저장될 때 자동으로 현재 시간 저장
    @Column(name = "created_at", updatable = false) //최초생성시간은 변경이 불가하도록
    private LocalDateTime createdAt;

    @UpdateTimestamp //엔티티가 수정될대마다 자동으로 업데이트 시간을 넣어줌
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
