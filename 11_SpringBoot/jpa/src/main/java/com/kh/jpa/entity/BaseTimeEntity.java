package com.kh.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass // 추후에 BaseTimeEntity를 상속한 엔티티들을 아래 필드들을 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // Auditing(자동으로 값 매핑) 기능 추가
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "CREATE_DATE", updatable = false)
    protected LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "MODIFY_DATE")
    protected LocalDateTime modifyDate;

    // Explicit getters for encapsulation
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }
}