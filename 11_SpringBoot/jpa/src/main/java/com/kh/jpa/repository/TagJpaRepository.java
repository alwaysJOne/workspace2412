package com.kh.jpa.repository;

import com.kh.jpa.entity.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Tag Repository - Spring Data JPA 버전
 * JpaRepository를 상속받아 기본 CRUD 메서드 자동 제공
 *
 * 기본 제공 메서드:
 * - save(Tag) : 저장 및 수정
 * - findById(Long) : ID로 조회
 * - findAll() : 전체 조회
 * - delete(Tag) : 삭제
 * - count() : 개수 조회
 * - existsById(Long) : 존재 여부 확인
 */
public interface TagJpaRepository extends JpaRepository<Tag, Long> {

    /**
     * 태그 이름으로 조회
     * Method Query - 메서드 이름으로 자동 쿼리 생성
     */
    Optional<Tag> findByTagName(String tagName);

    /**
     * 태그 이름으로 조회 (JPQL 버전)
     * @Query 애노테이션 사용
     */
    @Query("select t from Tag t where t.tagName = :tagName")
    Optional<Tag> findByTagNameWithQuery(@Param("tagName") String tagName);

    /**
     * 태그 이름 존재 여부 확인
     * Method Query
     */
    boolean existsByTagName(String tagName);

    /**
     * 태그 이름으로 검색 (LIKE 검색)
     * Method Query
     */
    List<Tag> findByTagNameContaining(String keyword);

    /**
     * 사용 중인 태그 조회 (게시글과 연결된 태그)
     * @Query 애노테이션 사용
     */
    @Query("select distinct t from Tag t join t.boardTags bt where bt.board.status = 'Y'")
    List<Tag> findUsedTags();

    /**
     * 인기 태그 조회 (게시글 수가 많은 순)
     * @Query 애노테이션 + COUNT 사용
     */
    @Query("select t from Tag t join t.boardTags bt " +
           "group by t.tagId, t.tagName " +
           "order by count(bt.boardTagId) desc")
    List<Tag> findPopularTags();

    /**
     * 특정 개수만큼 인기 태그 조회
     * @Query 애노테이션 + Limit
     */
    @Query(value = "select t.* from TAG t " +
                   "join BOARD_TAG bt on t.TAG_ID = bt.TAG_ID " +
                   "group by t.TAG_ID, t.TAG_NAME " +
                   "order by count(bt.BOARD_TAG_ID) desc " +
                   "limit :limit",
           nativeQuery = true)
    List<Tag> findTopNPopularTags(@Param("limit") int limit);
}
