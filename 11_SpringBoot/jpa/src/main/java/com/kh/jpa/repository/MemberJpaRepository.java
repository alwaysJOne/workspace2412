package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Member Repository - Spring Data JPA 버전
 * JpaRepository를 상속받아 기본 CRUD 메서드 자동 제공
 *
 * 기본 제공 메서드:
 * - save(Member) : 저장 및 수정
 * - findById(String) : ID로 조회
 * - findAll() : 전체 조회
 * - delete(Member) : 삭제
 * - count() : 개수 조회
 * - existsById(String) : 존재 여부 확인
 */
public interface MemberJpaRepository extends JpaRepository<Member, String> {

    /**
     * 이름으로 회원 검색 (LIKE 검색)
     * Method Query - 메서드 이름으로 자동 쿼리 생성
     */
    List<Member> findByUserNameContaining(String name);

    /**
     * 이름으로 회원 검색 (LIKE 검색) - JPQL 버전
     * @Query 애노테이션 사용
     */
    @Query("select m from Member m where m.userName LIKE %:username%")
    List<Member> findByNameWithQuery(@Param("username") String name);

    /**
     * 이메일로 회원 조회
     * Method Query
     */
    Member findByEmail(String email);

    /**
     * 나이 범위로 회원 조회
     * Method Query
     */
    List<Member> findByAgeBetween(Integer minAge, Integer maxAge);

    /**
     * 성별로 회원 조회
     * Method Query
     */
    List<Member> findByGender(Member.Gender gender);

    /**
     * 상태로 회원 조회
     * Method Query
     */
    @Query("select m from Member m where m.status = :status")
    List<Member> findByStatus(@Param("status") com.kh.jpa.enums.CommonEnums.Status status);
}
