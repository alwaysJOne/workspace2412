package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.entity.Member;
import com.kh.jpa.enums.CommonEnums;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Board Repository - Spring Data JPA 버전
 * JpaRepository를 상속받아 기본 CRUD 메서드 자동 제공
 *
 * 기본 제공 메서드:
 * - save(Board) : 저장 및 수정
 * - findById(Long) : ID로 조회
 * - findAll() : 전체 조회
 * - delete(Board) : 삭제
 * - count() : 개수 조회
 * - existsById(Long) : 존재 여부 확인
 */
public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    /**
     * 상태값으로 게시글 조회 (페이징 처리)
     * Method Query - 메서드 이름으로 자동 쿼리 생성
     */
    Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);

    /**
     * 작성자로 게시글 조회
     * Method Query
     */
    List<Board> findByMember(Member member);

    /**
     * 작성자 ID로 게시글 조회
     * Method Query with nested property
     */
    List<Board> findByMemberUserId(String userId);

    /**
     * 제목으로 게시글 검색 (LIKE 검색)
     * Method Query
     */
    List<Board> findByBoardTitleContaining(String title);

    /**
     * 제목 또는 내용으로 게시글 검색
     * Method Query
     */
    List<Board> findByBoardTitleContainingOrBoardContentContaining(String title, String content);

    /**
     * 상태별 게시글 조회 (리스트)
     * Method Query
     */
    List<Board> findByStatus(CommonEnums.Status status);

    /**
     * 조회수 높은 순으로 게시글 조회
     * Method Query with OrderBy
     */
    List<Board> findTop10ByStatusOrderByCountDesc(CommonEnums.Status status);

    /**
     * 특정 작성자의 활성 게시글 조회 (페이징)
     * @Query 애노테이션 사용
     */
    @Query("select b from Board b where b.member.userId = :userId and b.status = :status")
    Page<Board> findByUserIdAndStatus(@Param("userId") String userId,
                                       @Param("status") CommonEnums.Status status,
                                       Pageable pageable);

    /**
     * 게시글 검색 (제목 + 내용) with 페이징
     * @Query 애노테이션 사용
     */
    @Query("select b from Board b where b.status = :status " +
           "and (b.boardTitle like %:keyword% or b.boardContent like %:keyword%)")
    Page<Board> searchByKeyword(@Param("keyword") String keyword,
                                 @Param("status") CommonEnums.Status status,
                                 Pageable pageable);

    /**
     * 특정 태그를 가진 게시글 조회
     * Join 쿼리 사용
     */
    @Query("select distinct b from Board b " +
           "join b.boardTags bt " +
           "join bt.tag t " +
           "where t.tagName = :tagName and b.status = :status")
    Page<Board> findByTagName(@Param("tagName") String tagName,
                               @Param("status") CommonEnums.Status status,
                               Pageable pageable);

    /**
     * 조회수 증가
     * @Query 애노테이션 + @Modifying 사용
     */
    @Query("update Board b set b.count = b.count + 1 where b.boardNo = :boardNo")
    @org.springframework.data.jpa.repository.Modifying
    void incrementCount(@Param("boardNo") Long boardNo);
}
